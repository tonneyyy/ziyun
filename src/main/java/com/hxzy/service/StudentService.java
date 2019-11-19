package com.hxzy.service;

import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.BaseService;
import com.hxzy.entity.Student;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService extends BaseService<Student,Integer> {
    ResponseMessage saveStudent(Student student);

    /**
     * 批量上传
     * @param majorId
     * @param classesId
     * @param attach
     * @return
     */
    ResponseMessage batchImportStudent(int majorId, int classesId, MultipartFile attach);
}