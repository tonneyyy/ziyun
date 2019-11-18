package com.hxzy.vo;

import com.hxzy.common.search.PageSearch;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Author: Administrator
 * Date: 2019/11/18 11:17
 * Description: 请详细描述类的作用
 */

@Getter
@Setter
@ToString
public class StudentSearch extends PageSearch {
    private String id;
    private String name;
    private Integer majorId;
    private Integer classesId;
    private String mobile;
    private String sex;
    private Integer state;

}
