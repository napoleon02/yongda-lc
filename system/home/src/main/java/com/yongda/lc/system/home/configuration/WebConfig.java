package com.yongda.lc.system.home.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.yongda.lc.support.support.web.filter.XSSFilter;

/**
 * web相关配置
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/6-下午3:46
 */
@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean xssFilter() {
        FilterRegistrationBean xssFilter = new FilterRegistrationBean();
        xssFilter.setFilter(new XSSFilter());
        xssFilter.addUrlPatterns("/");
        xssFilter.setOrder(1);
        return xssFilter;
    }

}
