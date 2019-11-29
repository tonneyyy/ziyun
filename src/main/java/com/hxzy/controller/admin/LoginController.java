package com.hxzy.controller.admin;

import com.hxzy.common.bean.ResponseCodeEnum;
import com.hxzy.common.bean.ResponseMessage;
import com.hxzy.entity.Teacher;
import com.hxzy.service.TeacherService;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@Log4j2
@RequestMapping(value = "/admin")
public class LoginController {

    @Autowired
    public TeacherService teacherService;

    //后台登录界面
    @GetMapping(value = "/login")
    public String login(){
        return "admin/login";
    }

    @ResponseBody
    @PostMapping(value = "/loginExecute")
    public ResponseMessage loginExecute(HttpSession session, Teacher teacher,@RequestParam("account") String name){
        return this.teacherService.login(session,teacher,name);
    }


    @ResponseBody
    @PostMapping(value = "/loginValidator")
    public ResponseMessage executeLogin(Teacher teacher, Model model){
        //走shiro流程
        //1、创建一个用户对象(Subject 主体)
        Subject currentTeacher= SecurityUtils.getSubject();

        //2、创建一个验证token
        UsernamePasswordToken  token=new UsernamePasswordToken(teacher.getName(),teacher.getPassword());

        //6、登录
        try{
            //shiro会调用  spring-shiro.xml中配置 securityManager中的<property name="realm" ref="myRealm"/>
            //myRealm自己定义的类 com.hxzy.common.shiro.MyRealm
            currentTeacher.login(token);

            //7、判断是否登录成功
            boolean result=currentTeacher.isAuthenticated();
            if(result){
                //首页
                return new ResponseMessage(ResponseCodeEnum.SUCCESS);
            }
        }catch(UnknownAccountException ex1){
            log.info(ex1.getMessage());
            return new ResponseMessage(ResponseCodeEnum.USERNAME_PASSWORD_ERROR);
        }catch (IncorrectCredentialsException ex2){

            log.info(ex2.getMessage());
            return new ResponseMessage(ResponseCodeEnum.USERNAME_PASSWORD_ERROR);

        }catch(LockedAccountException ex3){
            log.info(ex3.getMessage());
            return new ResponseMessage(ResponseCodeEnum.USERNAME_Locked);

        }catch(AuthenticationException ex4){
            log.info(token.getUsername()+",其它错误");
            return new ResponseMessage(ResponseCodeEnum.ERROR,"未知错误");

        }
        return new ResponseMessage(ResponseCodeEnum.ERROR);
    }
}
