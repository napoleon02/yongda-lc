package com.yongda.lc.system.admin.web.interceptor;

import cn.hutool.json.JSONUtil;
import com.yongda.lc.dal.model.AdminDO;
import com.yongda.lc.support.annotation.LoginRequire;
import com.yongda.lc.support.constant.GlobalConst;
import com.yongda.lc.support.support.web.response.ResponseWrapper;
import com.yongda.lc.support.support.web.tool.WebTool;
import com.yongda.lc.system.admin.web.response.ResponseCode;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录校验拦截器
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/1/31-下午2:53
 */
public class LoginedInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object target) throws Exception {
        // TODO 开发期间免登录
        AdminDO adminDO = new AdminDO();
        adminDO.setId("63E262F1A48E041AE0531E6EA8C03611");
        adminDO.setName("管理员");
        HttpSession session = WebTool.getSession();
        session.setAttribute(GlobalConst.ADMIN_SESSION_KEY,adminDO);

        /*Class<?> targetClass = target.getClass();
        if (targetClass.isAssignableFrom(HandlerMethod.class)) {
            LoginRequire loginRequire = ((HandlerMethod) target).getMethodAnnotation(LoginRequire.class);
            if (null != loginRequire && !loginRequire.ignore()) {
                HttpSession session = WebTool.getSession();
                AdminDO adminDO = (AdminDO) session.getAttribute(GlobalConst.ADMIN_SESSION_KEY);
                //用户未登录
                if (null == adminDO) {
                    if (WebTool.isAjax()) {
                        response.setHeader("x-session-timeout", "TRUE");
                        WebTool.write(JSONUtil.toJsonStr(ResponseWrapper.newInstance(ResponseCode.SESSION_TIME_OUT)));
                    } else {
                        response.sendRedirect("/index.html");
                    }
                    return false;
                }

            }
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
