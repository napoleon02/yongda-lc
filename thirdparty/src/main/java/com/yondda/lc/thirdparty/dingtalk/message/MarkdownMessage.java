package com.yondda.lc.thirdparty.dingtalk.message;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 钉钉群机器人Markdown类型消息
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/8-下午4:13
 */
public class MarkdownMessage extends AtOption implements DingTalkRobotMessage {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容项
     */
    private List<String> items = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void add(String text) {
        items.add(text);
    }

    public static String getBoldText(String text) {
        return "**" + text + "**";
    }

    public static String getItalicText(String text) {
        return "*" + text + "*";
    }

    public static String getLinkText(String text, String href) {
        return "[" + text + "](" + href + ")";
    }

    public static String getImageText(String imageUrl) {
        return "![image](" + imageUrl + ")";
    }

    public static String getHeaderText(int headerType, String text) {
        if (headerType < 1 || headerType > 6) {
            throw new IllegalArgumentException("headerType should be in [1, 6]");
        }

        StringBuffer numbers = new StringBuffer();
        for (int i = 0; i < headerType; i++) {
            numbers.append("#");
        }
        return numbers + " " + text;
    }

    public static String getReferenceText(String text) {
        return "> " + text;
    }

    public static String getOrderListText(List<String> orderItem) {
        if (orderItem.isEmpty()) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= orderItem.size() - 1; i++) {
            sb.append(String.valueOf(i) + ". " + orderItem.get(i - 1) + "\n");
        }
        sb.append(String.valueOf(orderItem.size()) + ". " + orderItem.get(orderItem.size() - 1));
        return sb.toString();
    }

    public static String getUnorderListText(List<String> unorderItem) {
        if (unorderItem.isEmpty()) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < unorderItem.size() - 1; i++) {
            sb.append("- " + unorderItem.get(i) + "\n");
        }
        sb.append("- " + unorderItem.get(unorderItem.size() - 1));
        return sb.toString();
    }

    @Override
    public String toJson() {
        Map<String, Object> result = new HashMap<>();
        result.put("msgtype", "markdown");

        Map<String, Object> markdown = new HashMap<>();
        markdown.put("title", title);

        StringBuffer markdownText = new StringBuffer();
        for (String item : items) {
            markdownText.append(item + "\n");
        }

        markdown.put("text", markdownText.toString());
        result.put("markdown", markdown);

        Map<String, Object> atItems = new HashMap<>();
        if (getAtMobiles() != null && !getAtMobiles().isEmpty()) {
            atItems.put("atMobiles", getAtMobiles());
        }

        if (getAtAll()) {
            atItems.put("isAtAll", true);
        }
        result.put("at", atItems);

        return JSONUtil.toJsonStr(result);
    }

    @Override
    public String toString() {
        return "MarkdownMessage{" +
                "title='" + title + '\'' +
                ", items=" + items +
                "} " + super.toString();
    }
}
