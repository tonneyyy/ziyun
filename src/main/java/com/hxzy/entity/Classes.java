package com.hxzy.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Classes implements Serializable {
    private Integer id;

    private String name;

    private Integer marjorid;
    private String majorName;

    private String rules;

    private Integer startnum;


    //阿里fastjson自定义日期格式
    @JSONField(format="yyyy-MM-dd")
    //spring的网页来接收日期参数的格式，如果不设定会报400错误
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date openingdate;

    //所带班级的教师
    private String teachers;
}