package com.hxzy.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class LoginController {


    //后台登录界面
    @GetMapping(value = "/login")
    public String login(Model model){
        System.out.println("进入了控制器-----------");
        return "admin/login";
    }
}
