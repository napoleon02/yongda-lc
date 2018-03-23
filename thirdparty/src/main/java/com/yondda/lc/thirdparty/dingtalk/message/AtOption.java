package com.yondda.lc.thirdparty.dingtalk.message;

import java.util.Set;

/**
 * AT选项
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/8-下午4:24
 */
public class AtOption {

    /**
     * 被@人的手机号码
     */
    private Set<String> atMobiles;

    /**
     * 是否@所有人
     */
    private boolean isAtAll;

    public Set<String> getAtMobiles() {
        return atMobiles;
    }

    public void setAtMobiles(Set<String> atMobiles) {
        this.atMobiles = atMobiles;
    }

    public boolean getAtAll() {
        return isAtAll;
    }

    public void setAtAll(boolean atAll) {
        isAtAll = atAll;
    }

    @Override
    public String toString() {
        return "AtOption{" +
                "atMobiles=" + atMobiles +
                ", isAtAll=" + isAtAll +
                '}';
    }
}
