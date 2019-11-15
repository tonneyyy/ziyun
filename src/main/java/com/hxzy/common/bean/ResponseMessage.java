package com.hxzy.common.bean;

import lombok.Data;

/**
 * 自定义返回消息类
 */
@Data
public class ResponseMessage {

    /**
     * 返回消息码
     */
    private int code;

    /**
     * 消息码的提示信息
     */
    private String message;

    //返回消息内容
    private Object result;


    /**
     * 返回结果
     * @param responseCodeEnum
     */
    public ResponseMessage(ResponseCodeEnum responseCodeEnum){
        this.code=responseCodeEnum.getCode();
        this.message=responseCodeEnum.getMessage();
    }

    /**
     * 返回结果
     * @param responseCodeEnum
     * @param message  自定义消息
     */
    public ResponseMessage(ResponseCodeEnum responseCodeEnum,String message){
        this.code=responseCodeEnum.getCode();
        this.message=message;
    }

    /**
     * 返回结果 添加内容
     * @param responseCodeEnum
     * @param data  自定义消息
     */
    public ResponseMessage(ResponseCodeEnum responseCodeEnum,Object data){
        this.code=responseCodeEnum.getCode();
        this.message=responseCodeEnum.getMessage();
        this.result=data;
    }


    /**
     * 返回结果 添加内容和自定义消息
     * @param responseCodeEnum
     * @param data
     * @param message
     */
    public ResponseMessage(ResponseCodeEnum responseCodeEnum,Object data,String message){
        this.code=responseCodeEnum.getCode();
        this.message=message;
        this.result=data;
    }


}
