package com.yondda.lc.thirdparty.dingtalk;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yondda.lc.thirdparty.dingtalk.message.DingTalkRobotMessage;

/**
 * 钉钉群机器人客户端
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/8-下午4:57
 */
public class DingTalkRobotClient {

    /**
     * 钉钉群机器人webhook地址
     */
    private String webhook;

    public DingTalkRobotClient(String webhook) {
        this.webhook = webhook;
    }

    /**
     * 发送消息
     *
     * @param message 消息
     * @return 结果
     */
    public DingTalkRobotResult send(DingTalkRobotMessage message) {
        HttpResponse response = HttpUtil.createPost(this.webhook)
                .timeout(10000)
                .body(message.toJson()).execute();
        DingTalkRobotResult result;
        if (response.getStatus() == HttpStatus.HTTP_OK) {
            String body = response.body();
            JSONObject jsonObject = JSONUtil.parseObj(body);
            result = new DingTalkRobotResult();
            Integer errcode = jsonObject.getInt("errcode");
            result.setErrorCode(errcode);
            result.setErrorMsg(jsonObject.getStr("errmsg"));
            result.setIsSuccess(errcode.equals(0));
        } else {
            result = new DingTalkRobotResult();
            result.setErrorCode(Integer.MAX_VALUE);
            result.setErrorMsg("请求钉钉服务器失败");
            result.setIsSuccess(false);
        }
        return result;
    }
}
