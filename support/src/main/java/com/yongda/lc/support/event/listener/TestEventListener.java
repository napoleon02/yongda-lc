package com.yongda.lc.support.event.listener;

import com.yongda.lc.support.event.TestEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 测试事件监听器
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/5-下午3:39
 */
@Component
public class TestEventListener {

    @EventListener
    @Async(value = "threadPool")
    public void testEventListener(TestEvent testEvent) {
        System.out.println("test事件消费");
    }

}
