package com.yongda.lc.support.configuration;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.yondda.lc.thirdparty.dingtalk.DingTalkRobotClient;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/6-下午4:04
 */
@Configuration
@EnableAsync
public class GlobalConfig {

    /**
     * 线程池
     *
     * @return Executor
     */
    @Bean
    public Executor threadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(16);
        executor.setMaxPoolSize(64);
        executor.setQueueCapacity(16);
        executor.setThreadNamePrefix("threadPool_");
        //rejection-policy：当pool已经达到max size的时候，如何处理新任务
        //CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        //对拒绝task的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setKeepAliveSeconds(60);
        executor.initialize();
        return executor;
    }

    /**
     * 钉钉群机器人客户端
     *
     * @param webhook webhook地址
     * @return DingTalkRobotClient
     */
    @Bean
    public DingTalkRobotClient dingTalkRobotClient(@Value(value = "${dingtalk.robot.webhook}") String webhook) {
        if (StrUtil.isBlank(webhook)) {
            throw new IllegalArgumentException("钉钉群机器人webhook地址不能为空！");
        }
        return new DingTalkRobotClient(webhook);
    }

    /**
     * 将前台传递的日期字符串格式化成日期格式
     *
     * @return 返回日期类型
     */
    @Bean
    public Converter<String, Date> string2Date() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                if (StrUtil.isNotBlank(source)) {
                    DateTime parse = DateUtil.parse(source);
                    return parse;
                }
                return null;
            }
        };
    }

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
