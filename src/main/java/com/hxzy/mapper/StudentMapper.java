package com.hxzy.mapper;

import com.hxzy.common.mapper.BaseMapper;
import com.hxzy.entity.Student;
import org.springframework.stereotype.Component;


@Component
public interface StudentMapper extends BaseMapper<Student,Integer> {

    int insertSelective(Student student);

}