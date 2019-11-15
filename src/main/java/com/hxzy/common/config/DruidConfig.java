package com.hxzy.common.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 阿里的连接池
 */
@Configuration
public class DruidConfig {


    @Bean(initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties(prefix ="ailibaba.durid" )
    public DruidDataSource druidDataSource(){
        DruidDataSource dataSource=new DruidDataSource();
        //设定监听慢查询代理
        List<Filter> arr=new ArrayList<Filter>();
        arr.add(statFilter());
        dataSource.setProxyFilters( arr);

        return  dataSource;
    }

    @Bean
    public Filter statFilter(){
        StatFilter filter = new StatFilter();
        filter.setSlowSqlMillis(2000);  //慢查询sql语句( 只要是执行超过了5秒，都要去记录为慢查询)
        filter.setLogSlowSql(true);
        filter.setMergeSql(true);
        return filter;
    }

    //后台界面呢?
    //阿里的druid慢查询日志监听后台登录地址
    @Bean
    public ServletRegistrationBean druidServlet() {

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        // 登录地址
        servletRegistrationBean.addUrlMappings("/druid/*");

        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("loginUsername", "admin");// 用户名
        initParameters.put("loginPassword", "admin");// 密码
        initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
        initParameters.put("allow", ""); // IP白名单 (没有配置或者为空，则允许所有访问)
        //initParameters.put("deny", "192.168.20.38");// IP黑名单 (存在共同时，deny优先于allow)
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    //拦截器放行
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
