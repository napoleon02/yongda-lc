package com.yongda.lc.support.support.web.response;

/**
 * 抽象响应枚举
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/5-下午1:57
 */
public interface AbsResponseEnum {

    /**
     * 获取响应编码
     *
     * @return String
     */
    String getCode();

    /**
     * 获取响应消息
     *
     * @return String
     */
    String getMessage();

}
