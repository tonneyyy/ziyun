package com.hxzy.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Menu implements Serializable {
    private Integer id;

    private String menuName;

    private Integer menuLevel;

    private String actionName;


    private String authority;


    private Integer parentId;

    private Integer sort;

    private Integer state;


}

