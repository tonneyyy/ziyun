package com.hxzy.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.search.PageSearch;
import com.hxzy.common.util.DateUtil;
import com.hxzy.entity.Classes;
import com.hxzy.service.ClassesService;
import com.hxzy.vo.ClassesSearch;
import com.hxzy.vo.ClassesVO;
import com.hxzy.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author lz
 * @data 2019-11-15
 * @comment 描述
 */
@Controller
@RequestMapping(value = "/admin")
public class ClassesController {

    @Autowired
    private ClassesService classesService;



    @GetMapping("/classes/search")
    public String search(Model model){
        ResponseMessage marjor = this.classesService.findMajor();
        model.addAttribute("marjor",marjor.getResult());

        return "admin/classes/list";
    }

    @ResponseBody
    @PostMapping("/classes/data")
    public ResponseMessage ajaxData(PageSearch pageSearch){

        ResultData resultData = this.classesService.searchPage(pageSearch);

        return new ResponseMessage(ResponseCodeEnum.SUCCESS,resultData);
    }

    @ResponseBody
    @PostMapping(value = "/classes/save")
    public ResponseMessage sava(Classes classes,@RequestParam("openingDate") String date){
        boolean result = false;
//        Classes classes = new Classes();
//        classes.setId(classesVO.getId());
//        classes.setMarjorid(classesVO.getMarjorid());
//        classes.setName(classesVO.getName());
//        classes.setRules(classesVO.getRules());
//        classes.setStartnum(classesVO.getStartnum());
        Date date1 = DateUtil.stringToDate(date);
        classes.setOpeningdate(date1);
        if(classes.getId() == null || classes.getId() == 0){
            result = this.classesService.insert(classes);
        }else{
            result = this.classesService.update(classes);
        }
        ResponseMessage responseMessage = null;
        if(result){
            responseMessage = new ResponseMessage(ResponseCodeEnum.SUCCESS);
        }else{
            responseMessage = new ResponseMessage(ResponseCodeEnum.ERROR);
        }
        return responseMessage;
    }

    @ResponseBody
    @GetMapping(value = "/classes/{value}/findmarjor")
    public ResponseMessage findMarjor(@PathVariable("value") int id){
        return this.classesService.findMarjorByid(id);
    }


    @GetMapping(value = "/classes/{classesid}/ownteacher")
    public String showOwnRoleUserPage(@PathVariable("classesid") int classesId,Model model){
        Classes one = this.classesService.findOne(classesId);
        model.addAttribute("classes",one);
        return "admin/classes/ownteacher";
    }

    @ResponseBody
    @PostMapping(value = "/classes/ownteacher/data")
    public ResponseMessage ownUserData(ClassesSearch classesSearch){
        return this.classesService.ownTeacher(classesSearch);
    }

    @ResponseBody
    @PostMapping(value = "/classes/noownteacher/data")
    public ResponseMessage noOwnUserData(ClassesSearch classesSearch){
        return this.classesService.noOwnTeacher(classesSearch);
    }

    @ResponseBody
    @PostMapping(value = "/classes/saveRemove")
    public ResponseMessage saveRemove(@RequestParam("classesId") int classesId,@RequestParam("permsList[]") List<Integer> permsList){
        return this.classesService.saveRemove(classesId,permsList);
    }

    @ResponseBody
    @PostMapping(value = "/classes/saveAdd")
    public ResponseMessage saveAdd(@RequestParam("classesId") int classesId,@RequestParam("permsList[]") List<Integer> permsList){
        return this.classesService.saveAdd(classesId,permsList);
    }



}
