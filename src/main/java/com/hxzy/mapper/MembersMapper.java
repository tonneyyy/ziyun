package com.hxzy.mapper;

import com.hxzy.common.mapper.BaseMapper;
import com.hxzy.entity.Members;

import java.util.List;
import java.util.Set;

public interface MembersMapper extends BaseMapper<Members,Integer> {
//    全查
    List<Members> findAll();
    Members findByLoginName(String loginName);


}
