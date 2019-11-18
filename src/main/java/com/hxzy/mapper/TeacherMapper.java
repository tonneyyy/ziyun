package com.hxzy.mapper;

import com.hxzy.common.mapper.BaseMapper;
import com.hxzy.entity.Teacher;

public interface TeacherMapper extends BaseMapper<Teacher,Integer> {

    int exisName(String name);
}
