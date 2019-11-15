package com.hxzy.controller.admin;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 后台首页
 */
@RequestMapping(value = "/admin")
@Controller
public class IndexController {

    //首页
    @GetMapping(value = {"/", "/index"})
    public String index(HttpSession session){



        return "admin/index";
    }

    /**
     * 后台首页图表页
     * @return
     */
    @GetMapping(value = "/default")
    public String flotPage(){

        return "admin/default";
    }

    /**
     * 前6个月销售额的数据从数据库查询
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/sell/{n}/month")
    public JSONArray sellSixMonth(@PathVariable("n") int n){
        List<Date>  searchDateList=new ArrayList<>();
        for(int i=1;i<n;i++){
            LocalDateTime lt= LocalDateTime.now().minusMonths(  (n-i) );
            searchDateList.add(  localDateTime2Date(lt));
        }
        //本月
        searchDateList.add(new Date());

        //给数据库查询 二维数据
        /*
        [
            [1, 34],
            [2, 25],
            [3, 19],
            [4, 34],
            [5, 32],
            [6, 44]
        ]
         */
        JSONArray resultArray=new JSONArray();
       Random  rd=new Random();

        for(Date dt : searchDateList){
            //月份
            int month=dt.getMonth()+1;
            // select sum(销售额列) from 订单表 where  Year(数据库销售日期列)= Year(dt)   and  month(数据库销售日期列)=month(dt)
            int salayMoney=rd.nextInt(50)+5;
            JSONArray dtJson=new JSONArray();
            dtJson.add(month); //月份
            dtJson.add(salayMoney); //销售额

            resultArray.add(dtJson);
        }


        return resultArray;

    }


    public static Date localDateTime2Date( LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    public static void main(String[] args) {
        List<Date>  searchDateList=new ArrayList<>();

        int n=12;
        for(int i=1;i<n;i++){
            LocalDateTime lt= LocalDateTime.now().minusMonths(  (n-i) );
            searchDateList.add(  localDateTime2Date(lt));
        }
        //本月
        searchDateList.add(new Date());

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Date dt : searchDateList){
            System.out.println(  sdf.format(dt));
        }


        JSONArray resultArray=new JSONArray();
        Random  rd=new Random();

        for(Date dt : searchDateList){
            //月份
            int month=dt.getMonth()+1;
            // select sum(销售额列) from 订单表 where  Year(数据库销售日期列)= Year(dt)   and  month(数据库销售日期列)=month(dt)
            int salayMoney=rd.nextInt(50)+5;
            JSONArray dtJson=new JSONArray();
            dtJson.add(month); //月份
            dtJson.add(salayMoney); //销售额

            resultArray.add(dtJson);
        }

        System.out.println(resultArray.toJSONString());

    }

}
