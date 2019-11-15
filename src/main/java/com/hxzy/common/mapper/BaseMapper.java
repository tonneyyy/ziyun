package com.hxzy.common.mapper;

import com.hxzy.common.search.PageSearch;

import java.io.Serializable;
import java.util.List;

/**
 * 公共的数据访问 新增，修改，选择性更新，根据主键查询，分页查询
 * @param <Model> The Model Class 这里是泛型不是Model类
 * @param <PK> The Primary Key Class 如果是无主键，则可以用Model来跳过，如果是多主键则是Key类
 */
public interface BaseMapper<Model, PK extends Serializable> {

   /**
     * 新增操作
     * @param model
     * @return
     */
     int insert(Model model);

    /**
     * 更新所有列
     * @param model
     * @return
     */
    int update(Model model);

    /**
     * 选择性更新某些列(不为null)
     * @param model
     * @return
     */
    int updateSelective(Model model);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    Model  findOne(PK id);

    /**
     * 分页查询
     * @return
     */
    List<Model> searchPage(PageSearch pageSearch);


}
