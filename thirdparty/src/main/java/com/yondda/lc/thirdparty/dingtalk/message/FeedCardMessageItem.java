package com.yondda.lc.thirdparty.dingtalk.message;

/**
 * 钉钉群机器人FeedCard消息内容项
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/8-下午4:48
 */
public class FeedCardMessageItem {

    /**
     * 标题
     */
    private String title;

    /**
     * 图片URL
     */
    private String picURL;

    /**
     * 消息跳转地址
     */
    private String messageURL;

    public String getMessageURL() {
        return messageURL;
    }

    public void setMessageURL(String messageURL) {
        this.messageURL = messageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    @Override
    public String toString() {
        return "FeedCardMessageItem{" +
                "title='" + title + '\'' +
                ", picURL='" + picURL + '\'' +
                ", messageURL='" + messageURL + '\'' +
                '}';
    }
}
