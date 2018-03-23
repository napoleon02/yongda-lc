package com.yondda.lc.thirdparty.dingtalk.message;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 钉钉群机器人链接类型消息
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/8-下午4:03
 */
public class LinkMessage implements DingTalkRobotMessage {

    /**
     * 标题
     */
    private String title;

    /**
     * 消息内容，内容控制50字以内否则部分展示
     */
    private String text;

    /**
     * 图片URL
     */
    private String picUrl;

    /**
     * 点击消息跳转地址
     */
    private String messageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMessageUrl() {
        return messageUrl;
    }

    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl;
    }

    @Override
    public String toJson() {
        Map<String, Object> items = new HashMap<>();
        items.put("msgtype", "link");
        Map<String, String> linkContent = new HashMap<>();

        if (StrUtil.isBlank(this.title)) {
            throw new IllegalArgumentException("标题不能为空");
        } else {
            linkContent.put("title", this.title);
        }

        if (StrUtil.isBlank(this.messageUrl)) {
            throw new IllegalArgumentException("点击消息跳转地址不能为空");
        } else {
            linkContent.put("messageUrl", this.messageUrl);
        }

        if (StrUtil.isBlank(this.text)) {
            throw new IllegalArgumentException("消息内容不能为空");
        } else {
            linkContent.put("text", this.text);
        }

        if (StrUtil.isNotBlank(this.picUrl)) {
            linkContent.put("picUrl", this.picUrl);
        }

        items.put("link", linkContent);
        return JSONUtil.toJsonStr(items);
    }

    @Override
    public String toString() {
        return "LinkMessage{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", messageUrl='" + messageUrl + '\'' +
                '}';
    }
}

