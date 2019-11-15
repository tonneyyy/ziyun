package com.hxzy.common.service;

import com.hxzy.common.search.PageSearch;
import com.hxzy.vo.ResultData;

import java.io.Serializable;
import java.util.List;

/**
 * 公共的业务逻辑
 * @param <Model>
 * @param <PK>
 */
public interface BaseService<Model, PK extends Serializable> {

    /**
     * 新增操作
     * @param model
     * @return
     */
    boolean insert(Model model);

    /**
     * 更新所有列
     * @param model
     * @return
     */
    boolean update(Model model);

    /**
     * 选择性更新某些列(不为null)
     * @param model
     * @return
     */
    boolean updateSelective(Model model);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    Model  findOne(PK id);

    /**
     * 分页查询( 总记录数和 当前页数据)
     * @return
     */
    ResultData searchPage(PageSearch pageSearch);

}
