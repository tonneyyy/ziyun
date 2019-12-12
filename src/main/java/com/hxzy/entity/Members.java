package com.hxzy.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class Members implements Serializable {
    private Integer id;

    //用户名
    private String loginName;
//用户加密密码
    @JSONField(serialize = false)
    private String loginPassword;
   //手机号
    private String mobile;
    //盐
    @JSONField(serialize = false)
    private String salt;
    //注册时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date regDate;
//最后一次登录时间
    private Date lastLoginDate;
//是否是游客
    private Integer isGeust;
//是学生要加学生号
    private String studentId;

    //学员对象
    private Student student;

//状态  1.启用，0.禁用
    private Integer state;
}
