package com.hxzy.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 登录用户菜单
 */
@Getter
@Setter
public class MenuVO implements Serializable {
    private Integer id;

    private String menuName;

    private String actionName;

    private String authority;

    /**
     * 子节点，前台页在循环用的
     */
    private List<MenuVO> childrens;


}
