package com.yondda.lc.thirdparty.dingtalk.message;

/**
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/8-下午4:38
 */
public class ActionCardAction {

    /**
     * 标题
     */
    private String title;

    /**
     * 跳转URL
     */
    private String actionURL;

    public ActionCardAction(String text, String actionURL) {
        this.title = text;
        this.actionURL = actionURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActionURL() {
        return actionURL;
    }

    public void setActionURL(String actionURL) {
        this.actionURL = actionURL;
    }

    @Override
    public String toString() {
        return "ActionCardAction{" +
                "title='" + title + '\'' +
                ", actionURL='" + actionURL + '\'' +
                '}';
    }
}
