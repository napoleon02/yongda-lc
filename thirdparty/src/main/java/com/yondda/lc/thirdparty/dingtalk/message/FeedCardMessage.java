package com.yondda.lc.thirdparty.dingtalk.message;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 钉钉群机器人FeedCard类型消息
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/8-下午4:46
 */
public class FeedCardMessage implements DingTalkRobotMessage {

    private List<FeedCardMessageItem> feedItems;

    public List<FeedCardMessageItem> getFeedItems() {
        return feedItems;
    }

    public void setFeedItems(List<FeedCardMessageItem> feedItems) {
        this.feedItems = feedItems;
    }

    @Override
    public String toJson() {
        Map<String, Object> items = new HashMap<String, Object>();
        items.put("msgtype", "feedCard");

        Map<String, Object> feedCard = new HashMap<String, Object>();

        if (feedItems == null || feedItems.isEmpty()) {
            throw new IllegalArgumentException("feedItems should not be null or empty");
        }
        for (FeedCardMessageItem item : feedItems) {
            if (StrUtil.isBlank(item.getTitle())) {
                throw new IllegalArgumentException("title should not be blank");
            }
            if (StrUtil.isBlank(item.getMessageURL())) {
                throw new IllegalArgumentException("messageURL should not be blank");
            }
            if (StrUtil.isBlank(item.getPicURL())) {
                throw new IllegalArgumentException("picURL should not be blank");
            }
        }
        feedCard.put("links", feedItems);
        items.put("feedCard", feedCard);

        return JSONUtil.toJsonStr(items);
    }

    @Override
    public String toString() {
        return "FeedCardMessage{" +
                "feedItems=" + feedItems +
                '}';
    }
}
