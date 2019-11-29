package com.hxzy.config;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * Author: Administrator
 * Date: 2019/11/28 19:46
 * Description: 请详细描述类的作用
 */
@Component
public class FreemarkerConfig implements InitializingBean {
    @Autowired
    private Configuration configuration;
    @Autowired
    private FreeMarkerViewResolver resolver;
    @Override
    public void afterPropertiesSet() throws Exception {
        // 加上这句后，可以在页面上使用shiro标签
        configuration.setSharedVariable("shiro", new ShiroTags());
        // 加上这句后，可以在页面上用${context.contextPath}获取contextPath
//        resolver.setRequestContextAttribute("context");
    }
}
