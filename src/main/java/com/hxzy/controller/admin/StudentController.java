package com.hxzy.controller.admin;

import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.search.PageSearch;
import com.hxzy.common.validator.ValidatorImpl;
import com.hxzy.common.validator.ValidatorResult;
import com.hxzy.entity.Major;
import com.hxzy.entity.Student;
import com.hxzy.service.MajorService;
import com.hxzy.service.StudentService;
import com.hxzy.vo.ResultData;
import com.hxzy.vo.StudentSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: Administrator
 * Date: 2019/11/16 16:36
 * Comment: 注释
 */
@Controller
@RequestMapping("/admin")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private ValidatorImpl validatorImpl;

    /**
     * 转发到学生信息页面
     * @return 学生信息页面
     */
    @GetMapping(value = "/student/search")
    public String student(Model model){
        ResponseMessage rm = this.majorService.findAll();
        List<Major> result =( List<Major>) rm.getResult();
        model.addAttribute("majorList",result);
        return "admin/student/list";
    }

    /**
     * ajax分页请求学生信息数据
     * @param studentSearch 分页信息offset页数 limit每页条数 查询条件
     * @return 包装后响应的学生数据
     */
    @ResponseBody
    @PostMapping(value = "/student/data")
    public ResponseMessage ajaxData(StudentSearch studentSearch){
        //分页用的数据
        ResultData rs= this.studentService.searchPage(studentSearch);
        return  new ResponseMessage(ResponseCodeEnum.SUCCESS,rs);
    }

    /**
     * 新增或者修改学生保存操作
     * @param student 传入学生对象
     * @return 是否成功
     */
    @ResponseBody
    @PostMapping(value = "/student/save")
    public ResponseMessage saveStudent(Student student){

        //判断值是否有问题
        ValidatorResult validatorResult=validatorImpl.validate(student);
        //有错误
        if(validatorResult.isHasErrors()){
            return new ResponseMessage(ResponseCodeEnum.PARAMETER_ERROR, validatorResult.getErroMsgMap());
        }
        return this.studentService.saveStudent(student);
    }


    /**
     * 批量导入excel
     * @return 学生信息页面
     */
    @GetMapping(value = "/student/import")
    public String excelImport(Model model){
        ResponseMessage rm = this.majorService.findAll();
        List<Major> result =( List<Major>) rm.getResult();
        model.addAttribute("majorList",result);
        return "admin/student/batchImport";
    }

    /**
     * 批量上传文件导入数据库操作
     * @param model
     * @return
     */
    @PostMapping(value = "/student/batch")
    public String excelBatch(int majorId,int classesId,MultipartFile attach, Model model){

         ResponseMessage rm=this.studentService.batchImportStudent(majorId,classesId,attach);

         if(rm.getCode()==0){
             return "redirect:/admin/student/search";
         }else{
             model.addAttribute("error",rm.getMessage());

             //重新查询一次数据
             ResponseMessage rmMa = this.majorService.findAll();
             List<Major> result =( List<Major>) rmMa.getResult();
             model.addAttribute("majorList",result);

             return "admin/student/batchImport";
         }
    }

}
