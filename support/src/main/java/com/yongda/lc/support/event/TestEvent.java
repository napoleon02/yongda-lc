package com.yongda.lc.support.event;

import org.springframework.context.ApplicationEvent;

/**
 * 测试事件
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/5-下午3:37
 */
public class TestEvent extends ApplicationEvent {

    private static final long serialVersionUID = -2006824103241069771L;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public TestEvent(Object source) {
        super(source);
    }
}
