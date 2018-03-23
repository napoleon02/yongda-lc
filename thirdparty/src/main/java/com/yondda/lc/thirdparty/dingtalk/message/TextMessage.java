package com.yondda.lc.thirdparty.dingtalk.message;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 钉钉群机器人文本类型消息
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/8-下午3:47
 */
public class TextMessage extends AtOption implements DingTalkRobotMessage {

    /**
     * 消息内容
     */
    private String text;

    public TextMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toJson() {
        Map<String, Object> items = new HashMap<>();
        items.put("msgtype", "text");
        Map<String, String> textContent = new HashMap<>();
        if (StrUtil.isBlank(this.text)) {
            throw new IllegalArgumentException("消息内容不能为空");
        } else {
            textContent.put("content", this.text);
            items.put("text", textContent);
        }
        Map<String, Object> atItems = new HashMap<>();
        if (getAtMobiles() != null && !getAtMobiles().isEmpty()) {
            atItems.put("atMobiles", getAtMobiles());
        }
        if (getAtAll()) {
            atItems.put("isAtAll", true);
        }
        items.put("at", atItems);
        return JSONUtil.toJsonStr(items);
    }

    @Override
    public String toString() {
        return "DingTalkRobotDemo{" +
                "text='" + text + '\'' +
                "} " + super.toString();
    }
}
