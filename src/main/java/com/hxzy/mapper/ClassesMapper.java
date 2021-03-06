package com.hxzy.mapper;

import com.hxzy.common.mapper.BaseMapper;
import com.hxzy.entity.Classes;
import com.hxzy.entity.Major;
import com.hxzy.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassesMapper extends BaseMapper<Classes,Integer> {

    List<Major> findMajor();

    Major findMarjorByid(Integer id);

    List<Teacher> ownTeacher(int classesId);

    List<Teacher> noOwnTeacher(int classesId);

    int saveRemove(@Param("classesId") int classesId, @Param("list") List<Integer> permsList);


    int saveAdd(@Param("classesId") int classesId,@Param("list") List<Integer> permsList);


    /**
     * 根据专业查询半内开班的信息
     * @param majorId
     * @return
     */
    List<Classes>  halfHearClasses(int majorId);


}