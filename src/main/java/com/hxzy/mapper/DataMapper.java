package com.hxzy.mapper;

import com.hxzy.common.mapper.BaseMapper;
import com.hxzy.entity.Data;

import java.util.List;

/**
 * DataMapper继承基类
 */

public interface DataMapper extends BaseMapper<Data, Integer> {

    List<Data> findAll();

    int existTypesName(String name);

    List<Data> findData(int id);
}