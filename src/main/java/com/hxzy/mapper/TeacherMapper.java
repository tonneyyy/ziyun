package com.hxzy.mapper;

import com.hxzy.common.mapper.BaseMapper;
import com.hxzy.entity.Teacher;

import java.util.Set;

public interface TeacherMapper extends BaseMapper<Teacher,Integer> {

    int exisName(String name);

    Teacher findByName(String name);

    Set<String> findTeacherOwnRoleAuthority(int teacherId);

    Set<String> findTeacherOwnMenuAuthority(int teacherId);
}
