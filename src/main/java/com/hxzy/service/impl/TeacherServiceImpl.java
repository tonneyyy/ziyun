package com.hxzy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.search.PageSearch;
import com.hxzy.common.service.impl.BaseServiceImpl;

import com.hxzy.common.util.MD5Util;
import com.hxzy.entity.Major;
import com.hxzy.entity.Teacher;
import com.hxzy.mapper.MajorMapper;
import com.hxzy.mapper.TeacherMapper;
import com.hxzy.service.MenuService;
import com.hxzy.service.TeacherService;
import com.hxzy.vo.MenuVO;
import com.hxzy.vo.ResultData;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * describe:
 *
 * @author xxx
 * @date 2019/11/16
 */
@Log4j2
@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher,Integer> implements TeacherService{
    private TeacherMapper teacherMapper;

    @Autowired
    public void setTeacherMapper(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
        super.setBaseMapper(teacherMapper);
    }

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private MenuService menuService;

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
    public ResponseMessage login(HttpSession session, Teacher teacher,String name) {
        //1、先根据用户名查询用户
        Teacher dbTeacher= this.teacherMapper.findByName(name);
        if(dbTeacher==null){
            log.error(name+",用户名不存在的");
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

    @Override
    public ResultData searchPage(PageSearch pageSearch) {

         //专业
        List<Major> arrMajor=this.majorMapper.findAll();

        //分页查询
        PageHelper.offsetPage(pageSearch.getOffset(),pageSearch.getLimit());
        List<Teacher> rows=  this.teacherMapper.searchPage(pageSearch);


        for(Teacher  t :rows){
            //循环
            StringBuffer str=new StringBuffer();

            String know=t.getTeachKnowledge();
            if(StringUtils.isNotBlank(know)){
                String[]  arr=know.split(",");
                for(int i=0;i<arr.length;i++){
                    int  searchId=Integer.parseInt(arr[i]);
                    Major major=  arrMajor.stream().filter(p->p.getId()== searchId).findFirst().get();
                    str.append(major.getName());
                    if(i!= arr.length-1){
                        str.append(",");
                    }
                }
            }
            t.setTeachKnowledgeString(str.toString());
        }
        //强转
        Page pg=(Page) rows;

        //返回结果
        ResultData resultData=new ResultData();
        resultData.setRows(rows);
        resultData.setTotal(pg.getTotal());
        return resultData;
    }


    @Override
    public Teacher findByName(String name) {
        return this.teacherMapper.findByName(name);
    }

    @Override
    public Set<String> findTeacherOwnRoleAuthority(int teacherId) {
        return this.teacherMapper.findTeacherOwnRoleAuthority(teacherId);
    }

    @Override
    public Set<String> findTeacherOwnMenuAuthority(int teacherId) {
        return this.teacherMapper.findTeacherOwnMenuAuthority(teacherId);
    }

    /**
     * 加载用户菜单
     */
    @Override
    public void loadTeacherMenu(Session session) {
        Teacher currentTeacher = (Teacher) SecurityUtils.getSubject().getPrincipal();
        session.setAttribute("currentTeacher",currentTeacher);
        List<MenuVO> currentTeacherMenu = menuService.findTeacherMenu(currentTeacher.getId());
        session.setAttribute("currentTeacherMenu",currentTeacherMenu);
    }

}
