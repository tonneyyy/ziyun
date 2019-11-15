package com.hxzy.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 邮件发送结果
 */
@Getter
@Setter
public class SendEmailResult {
    private boolean success;

    private String message;
}
