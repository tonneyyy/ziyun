package com.hxzy.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * ztree使用
 */
@Getter
@Setter
public class ZTreeVO {

    private Integer id;

    private String name;

    /**
     * 父级节点ID
     */
    private Integer pId;
    /**
     * 是否展开
     */
    private Boolean open;
    /**
     *  是否选中
     */
    private Boolean checked;





}
