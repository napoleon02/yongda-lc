package com.yongda.lc.system.admin.web.response;

import com.yongda.lc.support.support.web.response.AbsResponseEnum;

/**
 * 后台系统返回码枚举
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/22-上午10:13
 */
public enum ResponseCode implements AbsResponseEnum {

    SESSION_TIME_OUT("409", "登录超时"),
    PARAMS_ERROR("P501", "参数错误");

    private String code;

    private String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
