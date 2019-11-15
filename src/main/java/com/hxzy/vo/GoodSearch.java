package com.hxzy.vo;

import com.hxzy.common.search.PageSearch;
import lombok.Getter;
import lombok.Setter;

/**
 * 产品查询条件
 */
@Getter
@Setter
public class GoodSearch extends PageSearch {

    private Integer pcate;

    private Integer ccate;

    private Integer tcate;

    private String goodssn;

    private String title;

    private Integer status;

    /**
     * 扩展值  (1=热卖，2=免邮,....)
     */
    private Integer extValue;


    /**
     * 排序方式
     */
    private String orderString=" g.createtime desc ";
}
