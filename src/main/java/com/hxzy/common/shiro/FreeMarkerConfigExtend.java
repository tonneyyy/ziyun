package com.hxzy.common.shiro;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * 让freemarker支持shiro标签库
 */
public class FreeMarkerConfigExtend  extends FreeMarkerConfigurer {
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        //设定freemarker页面可以使用 shiro标签库
        Configuration cfg = this.getConfiguration();
        cfg.setSharedVariable("shiro", new ShiroTags());//shiro标签
        cfg.setNumberFormat("#");//防止页面输出数字,变成2,000
    }
}
