package com.yongda.lc.system.admin.configuration;

import com.yongda.lc.system.admin.web.interceptor.LoginedInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * web相关配置
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/6-下午3:46
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //如果需要定义多个拦截器往里面添加即可

        //登录校验拦截器
        registry.addInterceptor(loginedInterceptor())
                .addPathPatterns("/**");

        super.addInterceptors(registry);
    }

    @Bean
    public LoginedInterceptor loginedInterceptor() {
        return new LoginedInterceptor();
    }

}
