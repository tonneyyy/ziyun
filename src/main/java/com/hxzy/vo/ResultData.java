package com.hxzy.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页查询返回的数据
 */
@Getter
@Setter
public class ResultData {
    //总共多少笔数据
    private long total;

    /**
     * 当前页的数据
     */
    private Object rows;

}
