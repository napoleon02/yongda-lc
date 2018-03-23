package com.yongda.lc.support.event.listener;

import cn.hutool.core.date.DateUtil;
import com.yondda.lc.thirdparty.dingtalk.DingTalkRobotClient;
import com.yondda.lc.thirdparty.dingtalk.message.MarkdownMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 系统监听器
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/8-下午3:04
 */
@Component
public class SystemEventListener {

    @Resource(name = "dingTalkRobotClient")
    private DingTalkRobotClient dingTalkRobotClient;

    @Resource(name = "environment")
    private Environment environment;

    @Value(value = "${server.port}")
    private Integer port;

    /**
     * 系统启动完毕事件
     *
     * @param event 事件源
     */
    @EventListener
    @Async(value = "threadPool")
    public void onFinishedEvent(ApplicationReadyEvent event) {
        MarkdownMessage message = new MarkdownMessage();
        message.setAtAll(true);
        message.setTitle("应用消息");
        message.add(MarkdownMessage.getBoldText("应用：" + environment.getProperty("server.display-name")));
        message.add("\n\n");
        message.add(MarkdownMessage.getBoldText("状态：启动"));
        message.add("\n\n");
        message.add(MarkdownMessage.getBoldText("端口：" + port));
        message.add("\n\n");
        message.add(MarkdownMessage.getBoldText("时间：" + DateUtil.formatDateTime(new Date())));
        message.add("\n\n");
        dingTalkRobotClient.send(message);
    }

    /**
     * 系统关闭事件
     *
     * @param event 事件源
     */
    @EventListener
    @Async(value = "threadPool")
    public void onClosedEvent(ContextClosedEvent event) {
        MarkdownMessage message = new MarkdownMessage();
        message.setAtAll(true);
        message.setTitle("应用消息");
        message.add(MarkdownMessage.getBoldText("应用：" + environment.getProperty("server.display-name")));
        message.add("\n\n");
        message.add(MarkdownMessage.getBoldText("状态：关闭"));
        message.add("\n\n");
        message.add(MarkdownMessage.getBoldText("端口：" + port));
        message.add("\n\n");
        message.add(MarkdownMessage.getBoldText("时间：" + DateUtil.formatDateTime(new Date())));
        message.add("\n\n");
        dingTalkRobotClient.send(message);
    }

    /**
     * servlet容器初始化事件
     *
     * @param event 事件源
     */
    @EventListener
    public void onServletContainerInitEvent(EmbeddedServletContainerInitializedEvent event) {
        this.port = event.getEmbeddedServletContainer().getPort();
    }
}