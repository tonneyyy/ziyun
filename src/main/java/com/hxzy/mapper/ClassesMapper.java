package com.hxzy.mapper;

import com.hxzy.common.mapper.BaseMapper;
import com.hxzy.entity.Classes;
import com.hxzy.entity.Marjor;
import com.hxzy.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassesMapper extends BaseMapper<Classes,Integer> {

    List<Marjor> findMajor();

    Marjor findMarjorByid(Integer id);

    List<Teacher> ownTeacher(int classesId);

    List<Teacher> noOwnTeacher(int classesId);

    int saveRemove(@Param("classesId") int classesId, @Param("list") List<Integer> permsList);


    int saveAdd(@Param("classesId") int classesId,@Param("list") List<Integer> permsList);
}