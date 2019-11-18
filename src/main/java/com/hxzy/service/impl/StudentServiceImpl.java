package com.hxzy.service.impl;

import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.impl.BaseServiceImpl;
import com.hxzy.entity.Classes;
import com.hxzy.entity.Student;
import com.hxzy.mapper.StudentMapper;
import com.hxzy.service.ClassesService;
import com.hxzy.service.StudentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            Classes one = classesService.findOne(student.getClassesId());
            if(one==null){
                return new ResponseMessage(ResponseCodeEnum.ERROR);
            }
            //组装学生ID
            String studentId=one.getRules()+one.getStartnum();
            one.setStartnum(one.getStartnum()+1);
            //更新学号开始数字
            this.classesService.update(one);
            //设置新增学生ID
            student.setId(studentId);
            result=this.studentMapper.insertSelective(student)>0;
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


}
