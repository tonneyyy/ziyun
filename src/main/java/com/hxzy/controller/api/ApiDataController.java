package com.hxzy.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.entity.Data;
import com.hxzy.service.DataService;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class ApiDataController {

    @Autowired
    private DataService dataService;

    /**
     * 得到文件上传需要的数据
     * @return
     */
    @GetMapping(value = "/datatype")
    @ResponseBody
    public ResponseMessage getData(){
        //原创
       List<Data> lablList=this.dataService.findData(1);
      //类型
        List<Data> contentList=this.dataService.findData(2);
        // 分类
        List<Data> typesList=this.dataService.findData(3);

        JSONObject  jsonObject =new JSONObject();
        jsonObject.put("labels", lablList);
        jsonObject.put("contents",contentList);
        jsonObject.put("types",typesList);
        //这个东西应该存放到redis

        return new ResponseMessage(ResponseCodeEnum.SUCCESS, jsonObject);
    }
}
