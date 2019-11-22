package com.hxzy.service;

import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.BaseService;
import com.hxzy.entity.Student;
import com.hxzy.vo.StudentSearch;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    XSSFWorkbook searchAll(StudentSearch studentSearch);
}