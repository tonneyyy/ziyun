package com.hxzy.common.util;

import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件工具类
 */
@Log4j2
public class FileUtil {

    //文件内容缓存内容(如果以后并发量了)  做高并发的
    private static ConcurrentHashMap<String,String>  fileTemplateCache=new ConcurrentHashMap<>();

    /**
     * 读取默认的找回密码的邮件模板
     * @return
     */
    public static String getForgetPasswordEmailTemplate(){
        return  fileTemplateCache.get("findpassword_template");
    }

    static{
         //读取邮件默认的内容
        try {
            initEmailTemplate();
        } catch (IOException e) {
            log.error("读取邮件模板文件失败:\n"+e.getMessage());
            e.printStackTrace();
        }
    }

    private static void initEmailTemplate() throws IOException {
        String filePath=FileUtil.class.getResource("/findpasswordTemplate.html").getPath();
        //读取内容
        String content=readEmailTemplate(filePath);
        //把内容放到缓存中
        fileTemplateCache.put("findpassword_template",content);

    }

    /**
     * 读取email模板
     * @param filePath 路径
     * @return
     */
    public static String readEmailTemplate(String filePath) throws IOException {

        StringBuffer  str=new StringBuffer();

        //把字节流byte-->字符流 char
        InputStreamReader  isr=new InputStreamReader(new FileInputStream(filePath),"UTF-8");

        BufferedReader  br=new BufferedReader(isr);
        //循环一行一行的读
        String temp;
        while(  (temp=br.readLine())!=null){
            str.append(temp);
        }
        br.close();

        return str.toString();
    }


    public static void main(String[] args) {

        for(int i=1;i<10;i++){

            Thread th=new Thread(new Runnable() {
                @Override
                public void run() {

                     String str=FileUtil.getForgetPasswordEmailTemplate();
                     str=str.replace("${username}","线程:"+Thread.currentThread().getId());

                    System.out.println(str);
                }
            });

            //启动线程
            th.start();
        }
    }


}
