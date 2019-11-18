package com.hxzy.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * sys_data
 * @author 
 */
@Getter
@Setter
public class Data {
    private Integer id;

    /**
     * 角色编号
     */
    private String name;

    /**
     * 1启用，0禁用
     */
    private Integer state;

    /**
     * 类型:  1、作品标签  2、作品内容，3、作品类型
     */
    private Integer types;

}