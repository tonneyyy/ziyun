package com.hxzy.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Author: Administrator
 * Date: 2019/11/16 14:36
 * Comment: 学生实体类
 */
@Data
public class Student implements Serializable {
    /**
     * 学号组成规则: classes(rules)学号前缀+自增数字classes(startNum)
     */
    private String id;

    /**
     * 学生名称
     */
    private String name;

    /**
     * 所学专业
     */
    private Integer majorId;

    /**
     * 专业名称
     */
    private String majorName;

    /**
     * 所在班级
     */
    private Integer classesId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 头像图片地址
     */
    private String portrait;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 入学日期
     */
    //spring的网页来接收日期参数的格式，如果不设定会报400错误
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date joinDate;

    /**
     * 性别
     */
    private String sex;

    /**
     * 出生年月
     */
    //spring的网页来接收日期参数的格式，如果不设定会报400错误
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    /**
     * 学历：1：本科  2：专科 3：专科以下  4:研究生
     */
    private Integer education;

    /**
     * 毕业院校
     */
    private String schoolName;

    /**
     * 学校所学专业
     */
    private String collegeMajor;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * qq号
     */
    private String qq;

    /**
     * 家庭住址
     */
    private String homeAddress;

    /**
     * 现住地址
     */
    private String currentAddress;

    /**
     * 状态:1正常   2：休学    3：退学 
     */
    private Integer state;

    private static final long serialVersionUID = 1L;
}