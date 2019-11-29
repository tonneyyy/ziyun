package com.hxzy.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class Members implements Serializable {
    private Integer id;

    //用户名
    private String loginName;
//用户加密密码
    private String loginPassword;
//手机号
    private String mobile;
//盐
    private String salt;
    //注册时间
    private Date regDate;
//最后一次登录时间
    private Date lastLoginDate;
//是否是游客
    private Integer isGeust;
//是学生要加学生号
    private String studentId;
//状态  1.启用，0.禁用
    private Integer state;
}
