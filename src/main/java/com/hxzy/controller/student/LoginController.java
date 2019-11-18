package com.hxzy.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "stuLoginController")
@RequestMapping("/student")
public class LoginController {

    @GetMapping(value = "/login")
    public String login(){
        return "student/login";
    }

}
