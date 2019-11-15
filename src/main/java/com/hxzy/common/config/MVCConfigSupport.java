package com.hxzy.common.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * SpirngMVC相关的配置(拦截器)
 */
@Configuration
@EnableAspectJAutoProxy
public class MVCConfigSupport extends WebMvcConfigurationSupport {


    //fastjson格式转换
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //自定义格式
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastConverter.setFastJsonConfig(fastJsonConfig);

        //解决乱码
        List<MediaType> supportedMediaTypes=new ArrayList<MediaType>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);

        converters.add(fastConverter);
    }

    /*
     <!--放行静态资源文件-->
    <mvc:resources mapping="/static/**" location="/static/"></mvc:resources>
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

    //自定义拦截器
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }


    //支持跨域访问
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                //允许任何域名
                .allowedOrigins("*")
                //允许访问请求方式
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                //允许携带cookie
                .allowCredentials(true).
                // 生存时间秒为位
                maxAge(3600);
    }


}
