package com.hxzy.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.search.PageSearch;
import com.hxzy.common.validator.ValidatorImpl;
import com.hxzy.common.validator.ValidatorResult;
import com.hxzy.entity.Major;
import com.hxzy.entity.Role;
import com.hxzy.service.MajorService;
import com.hxzy.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author
 * @date 20191115下午 5:46
 * @comment 请详细描述类的作用
 */
@Controller
@RequestMapping(value = "/admin")
public class MajorController {

    @Autowired
    private ValidatorImpl validatorImpl;

    @Autowired
    private MajorService majorService;

    @GetMapping(value = "/major/search")
    public String search(){
        return "admin/major/list";
    }

    @ResponseBody
    @PostMapping(value = "/major/data")
    public ResponseMessage ajaxData(PageSearch pageSearch){
        ResultData resultData = this.majorService.searchPage(pageSearch);

        return new ResponseMessage(ResponseCodeEnum.SUCCESS,resultData);
    }

    /**
     * 判断权限值是否存在
     * @param major
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/major/exist/name")
    public JSONObject ajaxExistAuthority(Major major){

        return  this.majorService.existMajorName(major);
    }

    /**
     * 新增或修改保存操作
     * @param major
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/major/save")
    public ResponseMessage save(Major major){

        //判断值是否有问题
        ValidatorResult validatorResult=validatorImpl.validate(major);
        //有错误
        if(validatorResult.isHasErrors()){
            return new ResponseMessage(ResponseCodeEnum.PARAMETER_ERROR, validatorResult.getErroMsgMap());
        }

        //角色后台还没有验证的

        boolean result=false;
        //新增
        if(major.getId()==null ||  major.getId()==0){
            result=this.majorService.insert(major);
        }else{
            result=this.majorService.update(major);
        }

        ResponseMessage responseMessage=null;
        if(result){
            responseMessage =new ResponseMessage(ResponseCodeEnum.SUCCESS);
        }else{
            responseMessage =new ResponseMessage(ResponseCodeEnum.ERROR);
        }

        return responseMessage;
    }
}
