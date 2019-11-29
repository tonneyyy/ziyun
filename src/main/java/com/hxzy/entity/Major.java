package com.hxzy.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author
 * @date 20191115下午 4:54
 * @comment 请详细描述类的作用
 */
@Getter
@Setter
public class Major implements Serializable {

    private Integer id;

    private String name;

    private Integer state;
}
