package com.hxzy.controller.admin;

import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/admin")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 显示用户查询的页面
     * @return
     */
    @GetMapping(value = "/menu/search")
    public String  search(){
        return "admin/menu/list";
    }


    /**
     * ajax查询请求
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/menu/data")
    public ResponseMessage ajaxData(){
        //再次组装，添加自定义返回码
        ResponseMessage responseMessage=this.menuService.findAll();

        return  responseMessage;

    }



}
