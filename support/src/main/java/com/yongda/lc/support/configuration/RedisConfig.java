package com.yongda.lc.support.configuration;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;

/**
 * redis配置
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/2-下午1:27
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName()).append(":").append(method.getName());
            for (Object obj : params) {
                sb.append("_").append(obj.toString());
            }
            return sb.toString();
        };
    }

}
