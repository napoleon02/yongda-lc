package com.yondda.lc.thirdparty.dingtalk.message;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 钉钉群机器人ActionCard类型消息
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/8-下午4:34
 */
public class ActionCardMessage implements DingTalkRobotMessage {

    public static final int MAX_ACTION_BUTTON_CNT = 5;

    public static final int MIN_ACTION_BUTTON_CNT = 1;

    /**
     * 标题
     */
    private String title;

    /**
     * banner图URL
     */
    private String bannerURL;

    /**
     * 简短标题
     */
    private String briefTitle;

    /**
     * 简短内容
     */
    private String briefText;

    /**
     * 是否显示头像
     */
    private boolean hideAvatar;

    private ActionButtonStyle actionButtonStyle = ActionButtonStyle.VERTICAL;

    private List<ActionCardAction> actions = new ArrayList<>();

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

    public ActionButtonStyle getActionButtonStyle() {
        return actionButtonStyle;
    }

    public void setActionButtonStyle(ActionButtonStyle actionButtonStyle) {
        this.actionButtonStyle = actionButtonStyle;
    }

    public String getBannerURL() {
        return bannerURL;
    }

    public void setBannerURL(String bannerURL) {
        this.bannerURL = bannerURL;
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

    public void addAction(ActionCardAction action) {
        if (actions.size() >= MAX_ACTION_BUTTON_CNT) {
            throw new IllegalArgumentException("number of actions can't more than " + MAX_ACTION_BUTTON_CNT);
        }
        actions.add(action);
    }

    @Override
    public String toJson() {
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("msgtype", "actionCard");

        Map<String, Object> actionCardContent = new HashMap<String, Object>();
        actionCardContent.put("title", title);

        StringBuffer text = new StringBuffer();
        if (StrUtil.isNotBlank(bannerURL)) {
            text.append(MarkdownMessage.getImageText(bannerURL) + "\n");
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

        if (actions.size() < MIN_ACTION_BUTTON_CNT) {
            throw new IllegalArgumentException("number of actions can't less than " + MIN_ACTION_BUTTON_CNT);
        }
        actionCardContent.put("btns", actions);

        if (actions.size() == 2 && actionButtonStyle == ActionButtonStyle.HORIZONTAL) {
            actionCardContent.put("btnOrientation", "1");
        }

        items.put("actionCard", actionCardContent);

        return JSONUtil.toJsonStr(items);
    }

    @Override
    public String toString() {
        return "ActionCardMessage{" +
                "title='" + title + '\'' +
                ", bannerURL='" + bannerURL + '\'' +
                ", briefTitle='" + briefTitle + '\'' +
                ", briefText='" + briefText + '\'' +
                ", hideAvatar=" + hideAvatar +
                ", actionButtonStyle=" + actionButtonStyle +
                ", actions=" + actions +
                '}';
    }
}
