package com.yongda.lc.system.admin.web.controller;

import com.yongda.lc.dal.model.AdminDO;
import com.yongda.lc.support.constant.GlobalConst;
import com.yongda.lc.support.support.web.tool.WebTool;

import javax.servlet.http.HttpSession;

/**
 * 后台系统基础控制器
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/22-上午9:52
 */
public class BaseController extends WebTool {

    /**
     * 获取当前登录的管理员信息
     *
     * @return AdminDO
     */
    public AdminDO getAdmin() {
        HttpSession session = getSession();
        return (AdminDO) session.getAttribute(GlobalConst.ADMIN_SESSION_KEY);
    }

}
