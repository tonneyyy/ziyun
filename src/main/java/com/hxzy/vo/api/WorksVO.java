package com.hxzy.vo.api;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * 首页学员作业
 */
@Getter
@Setter
public class WorksVO  {
    private Integer typeId;
    private String  typeName;
    //学员作品信息
    private List<JobtableVO> data;

}
