package com.yongda.lc.support.configuration;

import com.ibeetl.starter.BeetlTemplateConfig;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * beetl模板引擎配置
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/7-下午12:24
 */
@Configuration
public class BeetlEngineConfig {

    @Bean(name = "beetlCmsConfig")
    @ConditionalOnMissingBean(name = {"beetlCmsConfig"})
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        try {
            Properties extProperties = new Properties();
            extProperties.put("RESOURCE.autoCheck", "true");
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            if (loader == null) {
                loader = BeetlTemplateConfig.class.getClassLoader();
            }
            beetlGroupUtilConfiguration.setConfigProperties(extProperties);
            ClasspathResourceLoader cploder = new ClasspathResourceLoader(loader, "/beetl");
            beetlGroupUtilConfiguration.setResourceLoader(cploder);
            beetlGroupUtilConfiguration.init();
            //如果使用了优化编译器，涉及到字节码操作，需要添加ClassLoader
            beetlGroupUtilConfiguration.getGroupTemplate().setClassLoader(loader);
            return beetlGroupUtilConfiguration;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Bean(name = "cmsTemplate")
    @ConditionalOnMissingBean(GroupTemplate.class)
    public GroupTemplate getGroupTemplate(
            @Qualifier("beetlCmsConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        GroupTemplate gt = beetlGroupUtilConfiguration.getGroupTemplate();
        return gt;
    }

}
