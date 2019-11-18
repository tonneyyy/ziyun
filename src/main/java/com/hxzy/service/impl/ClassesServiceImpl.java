package com.hxzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.impl.BaseServiceImpl;
import com.hxzy.entity.Classes;
import com.hxzy.entity.Marjor;
import com.hxzy.entity.Teacher;
import com.hxzy.mapper.ClassesMapper;
import com.hxzy.service.ClassesService;
import com.hxzy.vo.ClassesSearch;
import com.hxzy.vo.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClassesServiceImpl extends BaseServiceImpl<Classes,Integer> implements ClassesService {

    private ClassesMapper classesMapper;

    @Autowired
    public void setClassesMapper(ClassesMapper classesMapper) {
        this.classesMapper = classesMapper;
        super.setBaseMapper(classesMapper);
    }

    @Override
    public ResponseMessage findMajor() {
        List<Marjor> marjor = this.classesMapper.findMajor();


        return new ResponseMessage(ResponseCodeEnum.SUCCESS, marjor);
    }

    @Override
    public ResponseMessage findMarjorByid(Integer id) {
        Marjor marjorByid = this.classesMapper.findMarjorByid(id);
        return new ResponseMessage(ResponseCodeEnum.SUCCESS,marjorByid);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResponseMessage ownTeacher(ClassesSearch classesSearch) {

        PageHelper.offsetPage(classesSearch.getOffset(),classesSearch.getLimit());

        List<Teacher> list = this.classesMapper.ownTeacher(classesSearch.getClassesId());

        Page pg = (Page)list;

        ResultData resultData = new ResultData();
        resultData.setRows(list);
        resultData.setTotal(pg.getTotal());

        return new ResponseMessage(ResponseCodeEnum.SUCCESS,resultData);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResponseMessage noOwnTeacher(ClassesSearch classesSearch) {

        PageHelper.offsetPage(classesSearch.getOffset(),classesSearch.getLimit());

        List<Teacher> list = this.classesMapper.noOwnTeacher(classesSearch.getClassesId());

        Page pg = (Page)list;

        ResultData resultData = new ResultData();
        resultData.setRows(list);
        resultData.setTotal(pg.getTotal());

        return new ResponseMessage(ResponseCodeEnum.SUCCESS,resultData);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResponseMessage saveRemove(int classesId, List<Integer> permsList) {
        ResponseMessage responseMessage = null;
        int result = this.classesMapper.saveRemove(classesId, permsList);
        if(result > 0){
            responseMessage = new ResponseMessage(ResponseCodeEnum.SUCCESS);
        }else{
            responseMessage = new ResponseMessage(ResponseCodeEnum.ERROR);
        }
        return responseMessage;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ResponseMessage saveAdd(int classesId, List<Integer> permsList) {
        ResponseMessage responseMessage = null;
        int result = this.classesMapper.saveAdd(classesId, permsList);
        if(result > 0){
            responseMessage = new ResponseMessage(ResponseCodeEnum.SUCCESS);
        }else{
            responseMessage = new ResponseMessage(ResponseCodeEnum.ERROR);
        }
        return responseMessage;
    }
}
