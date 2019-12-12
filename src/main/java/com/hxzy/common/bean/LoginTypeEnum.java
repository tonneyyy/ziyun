package com.hxzy.common.bean;

/**
 * 登录类型
 */
public enum LoginTypeEnum {
    USE_PASSWORD(1,"账号登录"),
    USE_MOBILE(2,"手机验证码登录");
    private int id;
    private String name;

    LoginTypeEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
