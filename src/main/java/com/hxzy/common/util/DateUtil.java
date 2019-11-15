package com.hxzy.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转整数
 */
public class DateUtil {

    /**
     * 日期转整数
     * @param date
     * @return
     */
    public static int dateToInt(Date  date){
        return (int)(date.getTime()/1000);
    }

    /**
     * 整数转日期
     * @param time
     * @return
     */
    public static Date intToDate(int time){
        return new Date(   time *1000L  );
    }

    /**
     * 把日期转换成字符串
     * @param date
     * @return
     */
    public static String dateToString(Date date){
        return dateToString(date,"yyyy-MM-dd");
    }

    /**
     * 把日期转换成字符串
     * @param date
     * @param  pattern 日期格式
     * @return
     */
    public static String dateToString(Date date,String pattern){
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 字符串转日期
     * @param str
     * @param pattern
     * @return
     */
    public static Date  stringToDate(String str,String pattern){
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        Date  dt=null;
        try {
            dt= sdf.parse(str);
        } catch (ParseException e) {
            dt=new Date();
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 字符串转日期
     * @param str
     * @return
     */
    public static Date  stringToDate(String str){
        return stringToDate(str,"yyyy-MM-dd");
    }
}
