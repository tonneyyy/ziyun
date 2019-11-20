package com.hxzy.controller.teacher;

import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.entity.Teacher;
import com.hxzy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller(value = "teaLoginController")
@RequestMapping("/teacher")
public class LoginController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping(value = "/login")
    public String login(){
        return "teacher/login";
    }
    @ResponseBody
    @PostMapping(value = "/loginExecute")
    public ResponseMessage loginExecute(HttpSession session, Teacher teacher,@RequestParam("account") String name){
        return this.teacherService.login(session,teacher,name);
    }

}
