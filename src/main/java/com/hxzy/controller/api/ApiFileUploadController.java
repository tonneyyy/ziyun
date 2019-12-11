package com.hxzy.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import io.netty.handler.codec.json.JsonObjectDecoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

@Log4j2
@Controller
@RequestMapping(value = "/api")
public class ApiFileUploadController {

    //主机
    @Value("${imgServer.host}")
    private String host;
    // url访问
    @Value("${imgServer.url}")
    private String url;
    //文件存放地址(绝对位置) F:/imgserver/
    @Value("${imgServer.filePath}")
    private String filePath;

    //文件后缀验证
   private static HashMap<String, String> extMap = new HashMap<String, String>();

   static{
       //支持的文件的后缀
       extMap.put("image", "jpg,jpeg,png");
       extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
       extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
   }

    /**
     * 文件上传   http://192.168.20.89:8080/api/worksupload?dir=image
     * @param file  springmvc文件拦截器
     * @param dir   参数
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/worksupload")
   public ResponseMessage fileUpload(MultipartFile  file,@RequestParam(value = "dir",defaultValue = "image") String dir){
        //文件保存绝对位置 F:/imgserver/works/
        String savePath=filePath+"works/";
        //返回给前端的url地址
        String saveUrl=host+url+"works/";


        ResponseMessage responseMessage=null;
        //单个文件大小( 10MB)
        long maxSize = 1024*1024*20;

        //文件大小为空
        if(file.isEmpty()) {
            return getErrorMessage("请选择要上传的文件");
        }
        //检查目录
        File uploadDir = new File(savePath);
        //如果它不是一个文件夹
        if(!uploadDir.isDirectory()){
            return getErrorMessage("图片服务器路径不存在");
        }
        //检查目录写权限
        if(!uploadDir.canWrite()){
            return getErrorMessage("上传目录没有写权限");
        }
        //判断参数是否有误
        if(!extMap.containsKey(dir)){
            return getErrorMessage("上传参数有误,dir必须是image/media/file");
        }
        //判断是否允许上传后缀
        //创建文件夹 年月日
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        savePath += ymd + "/";
        saveUrl += ymd + "/";
        File dirFile = new File(savePath);
        //如果文件夹不存在就要重新创建
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        //得到上传的原始名称
        String fileName = file.getOriginalFilename();
        //得到文件大小
        long fileSize = file.getSize();
        //检查文件大小
        if(fileSize > maxSize){
            return getErrorMessage("上传文件大小超过限制，单文件只允许在20MB之内.");
        }
        //检查扩展名
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        //extMap.get("image") = "jpg,jpeg,png".split(",")    ==> String[]  -->转集合 Arrays.<String>asList(String[]);
        if(!Arrays.<String>asList(extMap.get(dir).split(",")).contains(fileExt)){
            return getErrorMessage("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dir) + "格式。");
        }
        //再生成文件的名称 规则= yyyyMMddHHmmss_随机数.后缀名
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
        try{
            //创建新文件路径
            File newFile = new File(savePath, newFileName);
            //另存为操作
            file.transferTo(newFile);

            //成功 {url:"http://192.168.20.89:8080/imgServer/20191211/1111111_001.jpg"}
            JSONObject  json=new JSONObject();
            json.put("url",saveUrl + newFileName);
            responseMessage=new ResponseMessage(ResponseCodeEnum.SUCCESS,json);

        }catch(Exception e){
            log.error(e.getMessage());
            return getErrorMessage("上传文件失败");
        }

        return responseMessage;
   }

   //错误
   private ResponseMessage getErrorMessage(String message){
        return new ResponseMessage(ResponseCodeEnum.ERROR,message);
   }


}
