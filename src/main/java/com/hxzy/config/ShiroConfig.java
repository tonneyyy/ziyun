package com.hxzy.config;

import com.hxzy.common.shiro.MyRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author: Administrator
 * Date: 2019/11/28 17:19
 * Description: 请详细描述类的作用
 */
@Configuration
public class ShiroConfig {

    /**
     * redis管理配置
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("161.117.84.33:6379");
        redisManager.setPassword("zzz123");
        return redisManager;
    }

    @Bean
    public Realm myRealm(){
        MyRealm myRealm=new MyRealm();
        return myRealm;
    }

    /**
     * 安全管理
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //将自定义的realm交给SecurityManager管理
        securityManager.setRealm(myRealm());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(SessionManager());
        return securityManager;
    }

    /**
     * 配置Shiro的Web过滤器，拦截浏览器请求并交给SecurityManager处理
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //未登录的时候，会重定向这个页面
        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        //登录成功后如何跳转页面 ，重定向
        shiroFilterFactoryBean.setSuccessUrl("/admin/index");
        //访问该页面没有权限时候，重定向
        shiroFilterFactoryBean.setUnauthorizedUrl("/admin/unauthorized");
        //配置拦截链 使用LinkedHashMap,因为LinkedHashMap是有序的，shiro会根据添加的顺序进行拦截
        // Map<K,V> K指的是拦截的url V值的是该url是否拦截
        Map<String,String> filterChainMap = new LinkedHashMap<>(16);
        //anon:所有url都都可以匿名访问,先配置anon再配置auth c。
        filterChainMap.put("/static","anon");
        filterChainMap.put("/admin/login","anon");
        filterChainMap.put("/admin/loginValidator","anon");
        filterChainMap.put("/admin/unauthorized","anon");
        //auth c:所有url都必须认证通过才可以访问;
        filterChainMap.put("/admin/**", "authc");
        filterChainMap.put("/admin/teacher/**","authc,roles[admin,FinanceManager]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }


    /**
     * shiro的缓存管理使用redis需要redisManager
     */
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * redisSessionDAO属性需要redisManager
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 配置 shiro 中session管理  session会话管理器
     */
    @Bean
    public DefaultWebSessionManager SessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //删除失效的session
        sessionManager.setDeleteInvalidSessions(true);
        //设置sessionDao层的实现
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * 配置 Bean 后置处理器: 会自动的调用和 Spring 整合后各个组件的生命周期方法
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 生命周期
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }
}
