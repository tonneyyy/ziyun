package com.hxzy.mapper;

import com.hxzy.common.mapper.BaseMapper;
import com.hxzy.entity.Major;

import java.util.List;

/**
 * @author
 * @date 20191115下午 4:57
 * @comment 请详细描述类的作用
 */
public interface MajorMapper extends BaseMapper<Major,Integer> {

    List<Major> findAll();

    int existMajorName(String name);
}
