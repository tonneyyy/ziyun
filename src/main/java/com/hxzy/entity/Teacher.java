package com.hxzy.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Teacher {
    private Integer id;

    private String name;

    private String salt;

    private String password;

    private String teachknowledge;

    private String mobile;

    private String sex;

    private Date birthday;

    private Integer education;

    private Integer state;
}