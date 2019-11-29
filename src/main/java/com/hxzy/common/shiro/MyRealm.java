package com.hxzy.common.shiro;



import com.hxzy.common.util.MD5Util;
import com.hxzy.entity.Teacher;
import com.hxzy.service.TeacherService;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 自定义登录和授权操作(需要连接数据库)
 */
@Log4j2
public class MyRealm  extends AuthorizingRealm {

    @Autowired
    public TeacherService teacherService;

    //认证后的权限设定(Set<String> 字符串)
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo= new SimpleAuthorizationInfo();
        //得到登录认证时存放数据
        Teacher currentTeacher=(Teacher) principals.getPrimaryPrincipal();
        int userId=currentTeacher.getId();
        //查询它拥有的角色roles
        Set<String>  ownRoles=this.teacherService.findTeacherOwnRoleAuthority(userId);
        //用户角色集合
        authorizationInfo.addRoles(ownRoles);

        //查询它拥有的权限perms(目录菜单按钮)
       Set<String> ownPermis=this.teacherService.findTeacherOwnMenuAuthority(userId);
       authorizationInfo.addStringPermissions(ownPermis);
        return authorizationInfo;
    }

    //登录验证(用户名密码)
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws ShiroException {
        //将token转型
        if(token instanceof UsernamePasswordToken){
            //token中存放用户账号和密码
            UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken) token;
            String username = usernamePasswordToken.getUsername();

            //根据账号查询用户
            Teacher dbTeacher=this.teacherService.findByName(username);

            //验证账号
            if(dbTeacher==null){
                throw new UnknownAccountException(usernamePasswordToken.getUsername()+",用户名不存在");
            }
            //token中取出明文密码
            String pwd=new String(usernamePasswordToken.getPassword());
            //把明文变为MD5
            String md5Pwd= MD5Util.MD5Encode(pwd,dbTeacher.getSalt()  );
            //验证密码
            if( ! md5Pwd.equals(dbTeacher.getPassword())){
                throw new IncorrectCredentialsException(usernamePasswordToken.getUsername()+",用户名或密码错误");
            }

            //判断账户状态
            if(dbTeacher.getState()==0){
                throw new LockedAccountException(usernamePasswordToken.getUsername()+",账号被锁定");
            }

            //认证成功了 SimpleAuthenticationInfo(Object principal, Object credentials, String realmName)
            //自定义类
            Teacher  currentTeacher=new Teacher();
            currentTeacher.setId(dbTeacher.getId());
            currentTeacher.setName(dbTeacher.getName());
            //Object principal       存放对象(session)
            //Object credentials    明文密码   (如果不放，你就要重写 SimpleCredentialsMatcher中的doCredentialsMatch)
            //String realmName      唯一的名称
            //将用户信息存入redis
            return new SimpleAuthenticationInfo(currentTeacher, pwd,currentTeacher.getName()+"_realName");

        }

        return null;
    }
}
