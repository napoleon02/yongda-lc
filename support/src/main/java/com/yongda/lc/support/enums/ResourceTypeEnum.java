package com.yongda.lc.support.enums;

/**
 * @program: yongda-lc
 * @description: 权限资源类型枚举
 * @author: Napoleon
 * @create: 2018-03-23 11:14
 * @version: 0.0.1
 **/
public enum ResourceTypeEnum {

    RESOURCE_TYPE_MENU("MENU", "菜单"),

    RESOURCE_TYPE_LINK("LINK", "LINK链接"),

    RESOURCE_TYPE_BUTTON("BUTTON", "按钮");

    private String code;

    private String msg;

    ResourceTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
