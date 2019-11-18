package com.hxzy.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.validator.ValidatorImpl;
import com.hxzy.common.validator.ValidatorResult;
import com.hxzy.entity.Teacher;
import com.hxzy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * describe:
 *
 * @author xxx
 * @date 2019/11/16
 */
@Controller
@RequestMapping(value = "/admin")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ValidatorImpl validator;

    /**
     * 显示用户查询页面
     * @return
     */
    @GetMapping(value = "teacher/search")
    public String search(){

        return "admin/teacher/list";
    }

    /**
     * 判断名称是否存在
     * @param teacher
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/teacher/exist/username")
    public JSONObject ajaxExistName(Teacher teacher){
        return this.teacherService.existName(teacher);
    }

    /**
     * 新增或修改操作
     * @param teacher
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/teacher/save")
    public ResponseMessage save(Teacher teacher){
        //判断值是否有问题
        ValidatorResult validatorResult=validator.validate(teacher);
        //有错
        if (validatorResult.isHasErrors()){
            return  new ResponseMessage(ResponseCodeEnum.ERROR,validatorResult.getErroMsgMap());
        }
        boolean result=false;
        //新增
        if (teacher.getId()==null||teacher.getId()==0){
            result=this.teacherService.insert(teacher);
        }else {
            //修该先查询用户
            result=this.teacherService.update(teacher);
        }
        ResponseMessage responseMessage=null;
        if (result){
            responseMessage=new ResponseMessage(ResponseCodeEnum.SUCCESS);
        }else {
            responseMessage=new ResponseMessage(ResponseCodeEnum.ERROR);
        }
        return responseMessage;
    }

}