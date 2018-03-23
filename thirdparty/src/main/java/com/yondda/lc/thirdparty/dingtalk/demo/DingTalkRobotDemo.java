package com.yondda.lc.thirdparty.dingtalk.demo;

import cn.hutool.core.util.URLUtil;
import com.yondda.lc.thirdparty.dingtalk.DingTalkRobotClient;
import com.yondda.lc.thirdparty.dingtalk.DingTalkRobotResult;
import com.yondda.lc.thirdparty.dingtalk.message.MarkdownMessage;
import com.yondda.lc.thirdparty.dingtalk.message.TextMessage;
import org.junit.Test;

import java.util.ArrayList;

/**
 * 钉钉群机器人测试用例
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/8-下午5:26
 */
public class DingTalkRobotDemo {

    private static final String webhook = "https://oapi.dingtalk.com/robot/send?access_token=5753c44f30ddc3491cf0d08ed7f1ca6547a5f47e5b9c0c6bc28b4608b6798e89";

    private static final DingTalkRobotClient client = new DingTalkRobotClient(webhook);

    @Test
    public void textMessage() {
        TextMessage message = new TextMessage("大家好我是渣渣辉");
        DingTalkRobotResult result = client.send(message);
        System.out.println(result);
    }

    @Test
    public void markdownMessage() {
        MarkdownMessage message = new MarkdownMessage();
        message.setTitle("This is a markdown message");

        message.add(MarkdownMessage.getHeaderText(1, "header 1"));
        message.add(MarkdownMessage.getHeaderText(2, "header 2"));
        message.add(MarkdownMessage.getHeaderText(3, "header 3"));
        message.add(MarkdownMessage.getHeaderText(4, "header 4"));
        message.add(MarkdownMessage.getHeaderText(5, "header 5"));
        message.add(MarkdownMessage.getHeaderText(6, "header 6"));

        message.add(MarkdownMessage.getReferenceText("reference text"));
        message.add("\n\n");

        message.add("normal text");
        message.add("\n\n");

        message.add(MarkdownMessage.getBoldText("Bold Text"));
        message.add("\n\n");

        message.add(MarkdownMessage.getItalicText("Italic Text"));
        message.add("\n\n");

        ArrayList<String> orderList = new ArrayList<String>();
        orderList.add("order item1");
        orderList.add("order item2");
        message.add(MarkdownMessage.getOrderListText(orderList));
        message.add("\n\n");

        ArrayList<String> unorderList = new ArrayList<String>();
        unorderList.add("unorder item1");
        unorderList.add("unorder item2");
        message.add(MarkdownMessage.getUnorderListText(unorderList));
        message.add("\n\n");

        message.add(MarkdownMessage.getImageText("http://img01.taobaocdn.com/top/i1/LB1GCdYQXXXXXXtaFXXXXXXXXXX"));
        message.add(MarkdownMessage.getLinkText("This is a link", "dtmd://dingtalkclient/sendMessage?content=linkmessage"));
        message.add(MarkdownMessage.getLinkText("中文跳转", "dtmd://dingtalkclient/sendMessage?content=" + URLUtil.encode("链接消息", "UTF-8")));
        DingTalkRobotResult result = client.send(message);
        System.out.println(result);

    }


    @Test
    public void markdownMessage1() {
        MarkdownMessage message = new MarkdownMessage();
        message.setTitle("应用消息");
        message.add(MarkdownMessage.getBoldText("应用：理财后台管理系统"));
        message.add("\n\n");
        message.add(MarkdownMessage.getBoldText("状态：启动"));
        message.add("\n\n");
        message.add(MarkdownMessage.getBoldText("地址：127.0.0.1"));
        message.add("\n\n");
        message.add(MarkdownMessage.getBoldText("端口：3389"));
        message.add("\n\n");
        message.add(MarkdownMessage.getBoldText("时间：2018-03-09 10:10:10"));
        message.add("\n\n");
        DingTalkRobotResult result = client.send(message);
        System.out.println(result);
    }
}
