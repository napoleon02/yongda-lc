package com.yongda.lc.support.support.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 响应模型
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/5-下午1:34
 */
@ApiModel
public class ResponseWrapper<T> implements Serializable {

    private static final long serialVersionUID = -3373681610836130988L;

    @ApiModelProperty(value = "响应编码")
    private String code;

    @ApiModelProperty(value = "响应消息")
    private String msg;

    @ApiModelProperty(value = "响应数据")
    private T data;

    @ApiModelProperty(value = "是否调用成功")
    private boolean success;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public boolean getSuccess() {
        return success;
    }

    public ResponseWrapper<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 私有构造器
     *
     * @param code    响应编码
     * @param message 响应消息
     */
    private ResponseWrapper(String code, String message) {
        this.code = code;
        this.msg = message;
        assert DefaultResponseEnum.SUCCESS.getCode() != null;
        this.success = DefaultResponseEnum.SUCCESS.getCode().equals(code);
    }

    /**
     * 实例化
     *
     * @param absResponseEnum 抽象响应枚举
     * @return ResponseWrapper
     */
    public static ResponseWrapper newInstance(AbsResponseEnum absResponseEnum) {
        if (null == absResponseEnum) {
            absResponseEnum = DefaultResponseEnum.SUCCESS;
        }
        return new ResponseWrapper(absResponseEnum.getCode(), absResponseEnum.getMessage());
    }

    /**
     * 实例化
     *
     * @param code    响应编码
     * @param message 响应消息
     * @return ResponseWrapper
     */
    public static ResponseWrapper newInstance(String code, String message) {
        assert code != null && message != null;
        return new ResponseWrapper(code, message);
    }

    /**
     * 成功
     *
     * @return ResponseWrapper
     */
    public static ResponseWrapper ok() {
        return new ResponseWrapper(DefaultResponseEnum.SUCCESS.getCode(), DefaultResponseEnum.SUCCESS.getMessage());
    }

    /**
     * 失败
     *
     * @return ResponseWrapper
     */
    public static ResponseWrapper fail() {
        return new ResponseWrapper(DefaultResponseEnum.FAIL.getCode(), DefaultResponseEnum.FAIL.getMessage());
    }

    /**
     * 异常
     *
     * @return ResponseWrapper
     */
    public static ResponseWrapper error() {
        return new ResponseWrapper(DefaultResponseEnum.ERROR.getCode(), DefaultResponseEnum.ERROR.getMessage());
    }

    /**
     * 其它
     *
     * @param message 响应消息
     * @return ResponseWrapper
     */
    public static ResponseWrapper other(String message) {
        return new ResponseWrapper(DefaultResponseEnum.FAIL.getCode(), message);
    }
}
