package com.hxzy.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class Teacher implements Serializable {
    private Integer id;

    private String name;
    @JSONField(serialize = false)
    private String salt;
    @JSONField(serialize = false)
    private String password;

    private String teachKnowledge;
    //文字显示
    private String teachKnowledgeString;

    private String mobile;

    private String sex;
    //阿里fastjson自定义日期格式
    @JSONField(format="yyyy-MM-dd")
    //spring的网页来接收日期参数的格式，如果不设定会报400错误
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    private Integer education;

    private Integer state;
    private static final long serialVersionUID = 1L;
}