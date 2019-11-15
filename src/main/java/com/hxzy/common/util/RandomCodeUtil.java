package com.hxzy.common.util;

import lombok.extern.log4j.Log4j2;

import java.util.Random;

/**
 * 随机生成字符工具类
 */
@Log4j2
public class RandomCodeUtil {

    /**
     * 生成验证码(只是数字)
     * @param number 几个
     */
    public static String  randomCode(int number){
        if(number<=0 || number>10){
            log.error("生成数字长度只能是1-10位之间，用户设定的长度是:"+number);
            throw new RuntimeException("生成数字长度只能是1-10位之间");
        }

        StringBuffer str=new StringBuffer();
        Random rd=new Random();
        for(int i=1;i<=number;i++){
            int code=rd.nextInt(10);
            str.append(code);
        }
        return str.toString();
    }
}
