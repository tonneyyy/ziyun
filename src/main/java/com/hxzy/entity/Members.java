package com.hxzy.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Members {
    private Integer id;

    private String loginname;

    private String loginpassword;

    private String mobile;

    private String salt;

    private Date regdate;

    private Date lastlogindate;

    private Integer isgeust;

    private String studentid;

    private Integer state;

}