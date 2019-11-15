package com.hxzy.common.search;

import lombok.Getter;
import lombok.Setter;

/**
 * 通用分页查询类
 */
@Getter
@Setter
public class PageSearch {
    private Integer offset;
    private Integer limit;
}
