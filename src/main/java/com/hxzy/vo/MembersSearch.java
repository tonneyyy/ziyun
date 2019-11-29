package com.hxzy.vo;

import com.hxzy.common.search.PageSearch;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zzy
 * @date 2019-11-18 下午 5:42
 * @comment 请详细描述类的作用
 */

@Getter
@Setter
public class MembersSearch extends PageSearch {

    private String membersown;

    private String loginName;

    private String mobile;
}
