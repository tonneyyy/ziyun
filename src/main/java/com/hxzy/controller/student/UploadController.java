package com.hxzy.controller.student;

import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.entity.Data;
import com.hxzy.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author lz
 * @data 2019-11-19
 * @comment 描述
 */
@Controller
@RequestMapping(value = "/student")
public class UploadController {

    @Autowired
    private DataService dataService;

    @GetMapping("/upload")
    public String upload(Model model){
        List<Data> data01 = this.dataService.findData(1);
        List<Data> data02 = this.dataService.findData(2);
        List<Data> data03 = this.dataService.findData(3);
        model.addAttribute("data01",data01);
        model.addAttribute("data02",data02);
        model.addAttribute("data03",data03);
        return "student/page/shangchuan";
    }



}
