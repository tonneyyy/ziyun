package com.hxzy.common.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * 通用文件上传
 */
@Controller
public class FileUploadController {

    @Value("${imgServer.url}")
    private String imgServerMapping;  //映射地址

    @Value("${imgServer.filePath}")
    private String imgServerFilePath; //绝地目录

    //根据kineditor中upload_json.jsp来更改
    @ResponseBody
    @PostMapping(value = "/api/upload")
    public JSONObject fileUpload(MultipartFile imgFile, HttpServletRequest request){
          //文件保存目录路径  f:/imgServer/
        String savePath = imgServerFilePath;
        //文件保存目录URL
        String saveUrl  =imgServerMapping ;

        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        //允许上传图片后缀名
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        //允许上传视频
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        //允许上传其它文件的后缀
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");


        //最大文件大小 (字节)=大约BMB
        long maxSize = 1024L*1024L*5;

        //文件是空的
        if(imgFile.isEmpty()){
           return getError("请选择文件。");
        }

        //验证服务器路径是否存在
        File uploadDir = new File(savePath);
        if(!uploadDir.isDirectory()){
            return getError("图片服务器上传目录不存在。") ;
        }

        //检查目录写权限
        if(!uploadDir.canWrite()){
           return getError("图片服务器上传目录没有写权限。");
        }

       //得到本次上传的文件类型,如果没有，默认就当它是图片
        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }

        //http://localhost:8080/upload?dir=test  [image,flash,media,file]
        if(!extMap.containsKey(dirName)){
           return  getError("非法操作。") ;
        }

        //创建文件夹
        savePath += dirName + "/";
        saveUrl += dirName + "/";
        File saveDirFile = new File(savePath);
        //如果文件夹不存在就创建
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }

        //以日期为单位（年月日），来再一次创建文件夹
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        savePath += ymd + "/";
        saveUrl += ymd + "/";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        //得到上传文件的原始名称
        String fileName = imgFile.getOriginalFilename();
        //得到上传文件大小
        long fileSize = imgFile.getSize();
        //再来判断文件大小是否超过了限定
        if(fileSize > maxSize){
            return getError("上传文件大小超过限制(5MB)。");
        }

        //再来判断文件 后缀是否合法
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
            return getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
        }

        //开始上传文件,重命名文件
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
        try{
            File uploadedFile = new File(savePath, newFileName);

            //上传
            imgFile.transferTo(uploadedFile);

            JSONObject obj = new JSONObject();
            obj.put("error", 0);
            obj.put("url", saveUrl+  newFileName);
           return obj;

        }catch(Exception e){
          return getError("上传文件失败。");
        }

    }

    //返回错误信息
    private JSONObject getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj;
    }
}
