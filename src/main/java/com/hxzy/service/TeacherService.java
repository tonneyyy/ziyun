package com.hxzy.service;

import com.alibaba.fastjson.JSONObject;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.common.service.BaseService;
import com.hxzy.entity.Teacher;
import org.apache.shiro.session.Session;

import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * describe:
 *
 * @author xxx
 * @date 2019/11/16
 */
public interface TeacherService extends BaseService<Teacher,Integer> {
    /**
     * 判断名称是否存在
     * @param teacher
     * @return
     */
    JSONObject existName(Teacher teacher);

    ResponseMessage login(HttpSession session, Teacher teacher,String name);

    void loadTeacherMenu(Session session);

    Teacher findByName(String name);

    Set<String> findTeacherOwnRoleAuthority(int teacherId);

    Set<String> findTeacherOwnMenuAuthority(int teacherId);
}
