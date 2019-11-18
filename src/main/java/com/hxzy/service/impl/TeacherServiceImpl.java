package com.hxzy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.impl.BaseServiceImpl;
import com.hxzy.common.util.MD5Util;
import com.hxzy.entity.Teacher;
import com.hxzy.mapper.TeacherMapper;
import com.hxzy.service.TeacherService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

/**
 * describe:
 *
 * @author xxx
 * @date 2019/11/16
 */
@Log4j2
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

    @Override
    public ResponseMessage login(HttpSession session, Teacher teacher) {
        //1、先根据用户名查询用户
        Teacher dbTeacher= this.teacherMapper.findByName(teacher.getName());
        if(dbTeacher==null){
            log.error(teacher.getName()+",用户名不存在的");
            return new ResponseMessage(ResponseCodeEnum.USERNAME_PASSWORD_ERROR);
        }else{
            //明文密码-->MD5密码
            String md5Pwd= MD5Util.MD5Encode(teacher.getPassword(), dbTeacher.getSalt()  );

            if(md5Pwd.equals(  dbTeacher.getPassword())){

                //判断它是否账户被锁的
                if(dbTeacher.getState()==0){
                    log.error(dbTeacher.getName()+",用户账号被锁定");
                    return new ResponseMessage(ResponseCodeEnum.USERNAME_Locked);
                }else{
                    //存储用户信息到session，供全网站页面使用
                    dbTeacher.setPassword(null);
                    dbTeacher.setId(dbTeacher.getId());
                    session.setAttribute("currentUser",teacher);

                    return new ResponseMessage(ResponseCodeEnum.SUCCESS);
                }

            }else{
                log.error(teacher.getName()+",用户的密码输入错误的");
                return new ResponseMessage(ResponseCodeEnum.USERNAME_PASSWORD_ERROR);
            }
        }
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
