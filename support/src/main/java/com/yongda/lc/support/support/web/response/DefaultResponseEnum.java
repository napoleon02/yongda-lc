package com.yongda.lc.support.support.web.response;

/**
 * 默认响应枚举
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/5-下午1:59
 */
public enum DefaultResponseEnum implements AbsResponseEnum {

    SUCCESS("200", "成功"),
    FAIL("400", "失败"),
    ERROR("500", "异常");

    /**
     * 响应编码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    DefaultResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "DefaultResponseEnum{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                "} " + super.toString();
    }
}
