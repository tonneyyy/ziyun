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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * @param pageSearch 分页信息offset页数 limit每页条数
     * @return 包装后响应的学生数据
     */
    @ResponseBody
    @PostMapping(value = "/student/data")
    public ResponseMessage ajaxData(PageSearch pageSearch){
        //分页用的数据
        ResultData rs= this.studentService.searchPage(pageSearch);
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


}