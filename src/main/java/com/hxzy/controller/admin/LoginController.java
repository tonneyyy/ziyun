package com.hxzy.controller.admin;

import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.entity.Teacher;
import com.hxzy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin")
public class LoginController {

    @Autowired
    public TeacherService teacherService;

    //后台登录界面
    @GetMapping(value = "/login")
    public String login(Model model){
        System.out.println("进入了控制器-----------");
        return "admin/login";
    }

    @ResponseBody
    @PostMapping(value = "/loginExecute")
    public ResponseMessage loginExecute(HttpSession session, Teacher teacher){
        return this.teacherService.login(session,teacher);
    }
}
