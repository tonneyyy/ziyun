package com.hxzy.controller.registered;

import com.hxzy.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lz
 * @data 2019-11-19
 * @comment 描述
 */
@Controller
@RequestMapping(value = "/register")
public class registeredController {

    @Autowired
    private MembersService membersService;

    @GetMapping(value = "/index")
    public String register(){

        return "registered/registered";
    }

    @GetMapping(value = "/stepTwo")
    public String register2(){

        return "registered/registered2";
    }

    @GetMapping(value = "/stepThree")
    public String register3(){

        return "registered/registered3";
    }


}
