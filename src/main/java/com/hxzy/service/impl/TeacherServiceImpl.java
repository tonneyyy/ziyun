package com.hxzy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.common.service.impl.BaseServiceImpl;
import com.hxzy.common.util.MD5Util;
import com.hxzy.entity.Teacher;
import com.hxzy.mapper.TeacherMapper;
import com.hxzy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * describe:
 *
 * @author xxx
 * @date 2019/11/16
 */
@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher,Integer> implements TeacherService{
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    public void setTeacherMapper(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
        super.setBaseMapper(teacherMapper);
    }

    /**
     * 判断是否有该名称
     * @param teacher
     * @return
     */
    @Override
    public JSONObject existName(Teacher teacher) {
        boolean result=false;
        if (teacher.getId()==null||teacher.getId()==0){
            int count=this.teacherMapper.exisName(teacher.getName());
            result=(count==0);
        }else {
            //修改
            Teacher oldValue = this.teacherMapper.findOne(teacher.getId());
            //没有改
            if (oldValue.getName().equals(teacher.getName())){
                result=true;
            }else {
                int count=this.teacherMapper.exisName(teacher.getName());
                result=(count==0);
            }
        }
        //返回
        JSONObject mp=new JSONObject();
        mp.put("valid",result);
        return mp;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean insert(Teacher teacher) {
        //生成盐
        teacher.setSalt(MD5Util.randomSalt(5));
        //生成加密的字符串
        teacher.setPassword(MD5Util.MD5Encode(teacher.getPassword(),teacher.getSalt()));
        return super.insert(teacher);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean update(Teacher teacher) {
         Teacher dbteacher=this.teacherMapper.findOne(teacher.getId());
         //得到原来的盐
        if (dbteacher==null){
            return  false;
        }
        teacher.setSalt(dbteacher.getSalt());
        teacher.setPassword(MD5Util.MD5Encode(teacher.getPassword(),teacher.getSalt()));
        return super.update(teacher);
    }

}
