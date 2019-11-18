package com.hxzy.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.search.PageSearch;
import com.hxzy.common.validator.ValidatorImpl;
import com.hxzy.common.validator.ValidatorResult;
import com.hxzy.entity.Data;
import com.hxzy.service.DataService;
import com.hxzy.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author
 * @date 20191117下午 3:12
 * @comment 请详细描述类的作用
 */
@Controller
@RequestMapping(value = "/admin")
public class DataController {
    @Autowired
    private DataService dataService;

    @Autowired
    private ValidatorImpl validatorImpl;

    @GetMapping(value = "/data/search")
    public String search(){

        return "admin/data/list";
    }

    @ResponseBody
    @PostMapping(value = "/data/data")
    public ResponseMessage ajaxData(PageSearch pageSearch){
        ResultData resultData = this.dataService.searchPage(pageSearch);

        return new ResponseMessage(ResponseCodeEnum.SUCCESS,resultData);
    }

    /**
     * 判断权限值是否存在
     * @param data
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/data/exist/name")
    public JSONObject ajaxExistAuthority(Data data){

        return  this.dataService.existTypesName(data);
    }

    /**
     * 新增或修改保存操作
     * @param data
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/data/save")
    public ResponseMessage save(Data data){

        //判断值是否有问题
        ValidatorResult validatorResult=validatorImpl.validate(data);
        //有错误
        if(validatorResult.isHasErrors()){
            return new ResponseMessage(ResponseCodeEnum.PARAMETER_ERROR, validatorResult.getErroMsgMap());
        }

        //角色后台还没有验证的

        boolean result=false;
        //新增
        if(data.getId()==null ||  data.getId()==0){
            result=this.dataService.insert(data);
        }else{
            result=this.dataService.update(data);
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
