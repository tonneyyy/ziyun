package com.hxzy.common.bean;

/**
 * 响应码
 */
public enum ResponseCodeEnum {
    /*通用码*/
    SUCCESS(0,"成功"),
    ERROR(-1,"操作失败"),
    PARAMETER_ERROR(500,"参数赋值有错误"),
    TOKEN_INVALID(501,"您所用的 ID 不存在或已过期"),

    /*用户模块*/
    LoginFailed(1000001,"登录失败"),
    USERNAME_PASSWORD_ERROR(100002,"用户名或密码错误!"),
    USERNAME_Locked(100003,"用户账户被锁定，请<a href='/admin/findUser'>申请</a>解锁!"),


    ;

    /**
     * 响应码枚举类
     * @param code
     * @param message
     */
    ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
