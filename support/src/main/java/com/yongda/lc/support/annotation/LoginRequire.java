package com.yongda.lc.support.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 登录要求注解
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/22-上午9:54
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequire {

    /**
     * 忽略
     *
     * @return boolean
     */
    boolean ignore() default false;

}
