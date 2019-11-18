package com.hxzy.service;

import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.BaseService;
import com.hxzy.entity.Student;

public interface StudentService extends BaseService<Student,Integer> {
    ResponseMessage saveStudent(Student student);

}