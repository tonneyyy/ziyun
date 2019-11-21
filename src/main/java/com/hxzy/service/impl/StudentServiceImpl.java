package com.hxzy.service.impl;

import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.impl.BaseServiceImpl;
import com.hxzy.common.util.RedisUtil;
import com.hxzy.entity.Classes;
import com.hxzy.entity.Student;
import com.hxzy.mapper.StudentMapper;
import com.hxzy.service.ClassesService;
import com.hxzy.service.StudentService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student,Integer> implements StudentService {

    private StudentMapper studentMapper;

    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
        super.setBaseMapper(studentMapper);
    }

    @Autowired
    private ClassesService classesService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 新增或者修改学生信息
     * @param student 传入学生信息
     * @return 是否成功
     */
    @Override
    public ResponseMessage saveStudent(Student student) {
        boolean result=false;
        //判断新增还是修改
        if(student.getId()==null ||  student.getId().equals("")){
            Classes classes = classesService.findOne(student.getClassesId());
            if(classes==null){
                return new ResponseMessage(ResponseCodeEnum.ERROR);
            }

            //redis key规则
            String redisKey="classes_"+classes.getId();

            //前缀
            String prefix=classes.getRules();
            //自增数
            int startNum=classes.getStartnum();

            //判断redis中是否有缓存
            Object rv=this.redisUtil.get(redisKey);
            if(rv!=null){
                startNum= Integer.parseInt(rv.toString());
            }else{
                redisUtil.set(redisKey,startNum+"");
            }
            //自增
            startNum=(int)this.redisUtil.incr(redisKey);
            //设置学生
            if(startNum<10){
                student.setId(prefix+"0"+startNum);
            }else{
                student.setId(prefix+startNum);
            }

            //开始新增
            int i = this.studentMapper.insertSelective(student);

            if(i>0){
                classes.setStartnum(startNum);
                result= this.classesService.updateSelective(classes);
            }
            String key="classes_"+classes.getId();
            redisUtil.del(key);
        }else{
            result=this.studentMapper.updateSelective(student)>0;
        }
        ResponseMessage responseMessage=null;
        if(result){
            responseMessage =new ResponseMessage(ResponseCodeEnum.SUCCESS);
        }else{
            responseMessage =new ResponseMessage(ResponseCodeEnum.ERROR);
        }
        return responseMessage;
    }

    //批量上传
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ResponseMessage batchImportStudent(int majorId, int classesId, MultipartFile attach) {
        ResponseMessage  rm=null;
        //数据错误
        if(majorId==0 ){
            rm=new ResponseMessage(ResponseCodeEnum.PARAMETER_ERROR,"必须选择专业信息");
        }else if(classesId==0){
            rm=new ResponseMessage(ResponseCodeEnum.PARAMETER_ERROR,"必须选择班级信息");
        }else{

            if(attach.isEmpty()){
                rm=new ResponseMessage(ResponseCodeEnum.ERROR,"文件未选择或上传文件内容为空!");
            }else{
              //读取Excel操作
                List<Student> arrList=readExcelContent(majorId,classesId,attach);
                //批量插入数据库 成功 (删除key)
                boolean result=batchInsertStudent(arrList,classesId);
                if(result){
                    rm=new ResponseMessage(ResponseCodeEnum.SUCCESS);
                }else{
                    rm=new ResponseMessage(ResponseCodeEnum.ERROR);
                }
            }

        }
        return rm;
    }

    //批量插入
    private boolean batchInsertStudent(List<Student> arrList, int classesId) {
        int result=this.studentMapper.insertBatch(arrList);
        String key="classes_"+classesId;
        if(result>0){
            //更新班级数据库的值
            Classes  classes=new Classes();
            classes.setId(classesId);

            String redisKey="classes_"+classesId;
            //判断redis中是否有缓存
            Object redisValue=this.redisUtil.get(redisKey);
            classes.setStartnum(Integer.parseInt(redisValue.toString()));
            boolean resultUpdate=this.classesService.updateSelective(classes);
            //删除key
            redisUtil.del(key);
            return resultUpdate;
        }else {
            //失败一定要删除key
            redisUtil.del(key);
            return false;
        }

    }

    private List<Student> readExcelContent(int majorId, int classesId, MultipartFile attach) throws RuntimeException{
        //创建excel应用程序
        List<Student>   arrList=new ArrayList<>();

        //读取班级
        Classes  classes=this.classesService.findOne(classesId);

        //得到班级startNum
        String prefix=classes.getRules();   //学号前缀
        int startNum=classes.getStartnum();  //学号数字
        String redisKey="classes_"+classes.getId();

        //判断redis中是否有缓存
        Object redisValue=this.redisUtil.get(redisKey);
        if(redisValue==null){
            redisUtil.set(redisKey,startNum+"");
        }else{
            startNum= Integer.parseInt(redisValue.toString());
        }



        try {
            Workbook  wb=new XSSFWorkbook( attach.getInputStream());
            //读取excel表信息
            Sheet sheet= wb.getSheetAt(0);
            //3、循环从第2行(下标为1)开始读
            int i=1;
            //还没有读完
            boolean isNotEnd=true;
            while(isNotEnd){
                //3、得到行
                Row rows=sheet.getRow(i);
                //读完了
                if(rows==null){
                    break;
                }

                //创建一个学生对象
                Student  stu=new Student();
                //还差学号
                if(startNum<10){
                    stu.setId(prefix+"0"+startNum);
                }else{
                    stu.setId(prefix+startNum);
                }

                stu.setClassesId(classesId);
                stu.setMajorId(majorId);
                stu.setState(1);

                //入学日期
                Cell cell01=rows.getCell(0);
                stu.setJoinDate(cell01.getDateCellValue());

                //姓名
                Cell cell02=rows.getCell(1);
                stu.setName(cell02.getStringCellValue().trim());

                //性别
                Cell cell03=rows.getCell(2);
                stu.setSex(cell03.getStringCellValue().trim());

                //出生日期
                Cell cell04=rows.getCell(3);
                stu.setBirthday(cell04.getDateCellValue());

                //学历
                Cell cell05=rows.getCell(4);
                String edu=cell05.getStringCellValue().trim();
                //1：本科  2：专科 3：专科以下  4:研究生
                String[]  eduArr={"本科","专科","专科以下","研究生"};
                for(int z=0;z<eduArr.length;z++){
                    if(eduArr[z].equals(edu)){
                        stu.setEducation( z+1);
                    }
                }

                //毕业院校
                Cell cell06=rows.getCell(5);
                stu.setSchoolName(cell06.getStringCellValue().trim());
                //大学所学专业
                Cell cell07=rows.getCell(6);
                stu.setCollegeMajor(cell07.getStringCellValue().trim());
                //身份证号码
                 Cell cell08=rows.getCell(7);
                stu.setIdCard(cell08.getStringCellValue().trim());

                //手机号码
                Cell cell09=rows.getCell(8);
                stu.setMobile(cell09.getStringCellValue().trim());
                //QQ号码
                Cell cell10=rows.getCell(9);
                stu.setQq(cell10.getStringCellValue().trim());
                //家庭住址
                Cell cell11=rows.getCell(10);
                stu.setHomeAddress(cell11.getStringCellValue().trim());
                //现住地址
                Cell cell12=rows.getCell(11);
                stu.setCurrentAddress(cell12.getStringCellValue().trim());

                //更新redis，并且返回的结果赋值给变量
                startNum=(int)this.redisUtil.incr(redisKey);


                arrList.add(stu);

                i++;
            }

            //关闭excel程序
            wb.close();

        } catch (IOException e) {
             throw new RuntimeException(e.getMessage());
        }

        return arrList;

    }

}
