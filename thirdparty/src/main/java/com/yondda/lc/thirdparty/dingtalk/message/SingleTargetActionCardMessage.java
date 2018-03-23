package com.yondda.lc.thirdparty.dingtalk.message;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 钉钉群机器人单个ActionCard消息
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/8-下午4:51
 */
public class SingleTargetActionCardMessage implements DingTalkRobotMessage {

    /**
     * 标题
     */
    private String title;

    /**
     * banner图片
     */
    private String bannerUrl;

    /**
     * 简短标题
     */
    private String briefTitle;

    /**
     * 简短内容
     */
    private String briefText;

    /**
     * 单个按钮标题
     */
    private String singleTitle;

    /**
     * 跳转URL
     */
    private String singleURL;

    /**
     * 是否显示头像
     */
    private boolean hideAvatar;

    public boolean isHideAvatar() {
        return hideAvatar;
    }

    public void setHideAvatar(boolean hideAvatar) {
        this.hideAvatar = hideAvatar;
    }

    public String getBriefTitle() {
        return briefTitle;
    }

    public void setBriefTitle(String briefTitle) {
        this.briefTitle = briefTitle;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBriefText() {
        return briefText;
    }

    public void setBriefText(String briefText) {
        this.briefText = briefText;
    }

    public String getSingleTitle() {
        return singleTitle;
    }

    public void setSingleTitle(String singleTitle) {
        this.singleTitle = singleTitle;
    }

    public String getSingleURL() {
        return singleURL;
    }

    public void setSingleURL(String singleURL) {
        this.singleURL = singleURL;
    }

    @Override
    public String toJson() {
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("msgtype", "actionCard");

        Map<String, Object> actionCardContent = new HashMap<String, Object>();
        actionCardContent.put("title", title);

        StringBuffer text = new StringBuffer();
        if (StrUtil.isNotBlank(bannerUrl)) {
            text.append(MarkdownMessage.getImageText(bannerUrl) + "\n");
        }
        if (StrUtil.isNotBlank(briefTitle)) {
            text.append(MarkdownMessage.getHeaderText(3, briefTitle) + "\n");
        }
        if (StrUtil.isNotBlank(briefText)) {
            text.append(briefText + "\n");
        }
        actionCardContent.put("text", text.toString());

        if (hideAvatar) {
            actionCardContent.put("hideAvatar", "1");
        }
        if (StrUtil.isBlank(singleTitle)) {
            throw new IllegalArgumentException("singleTitle should not be blank");
        }
        if (StrUtil.isBlank(singleURL)) {
            throw new IllegalArgumentException("singleURL should not be blank");
        }

        actionCardContent.put("singleTitle", singleTitle);
        actionCardContent.put("singleURL", singleURL);

        items.put("actionCard", actionCardContent);

        return JSONUtil.toJsonStr(items);
    }

    @Override
    public String toString() {
        return "SingleTargetActionCardMessage{" +
                "title='" + title + '\'' +
                ", bannerUrl='" + bannerUrl + '\'' +
                ", briefTitle='" + briefTitle + '\'' +
                ", briefText='" + briefText + '\'' +
                ", singleTitle='" + singleTitle + '\'' +
                ", singleURL='" + singleURL + '\'' +
                ", hideAvatar=" + hideAvatar +
                '}';
    }
}
