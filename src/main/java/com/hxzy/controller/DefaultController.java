package com.hxzy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    /**
     * 项目默认重定向的页面
     * @return
     */
    @GetMapping(value = "/")
    public String index(){
        return "redirect:/admin/login";
    }
}
