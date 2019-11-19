package com.hxzy.mapper;

import com.hxzy.common.mapper.BaseMapper;
import com.hxzy.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface StudentMapper extends BaseMapper<Student,Integer> {

    int insertSelective(Student student);

    /**
     * 批量插入学生信息
     * @param list
     * @return
     */
    int insertBatch(List<Student> list);
}