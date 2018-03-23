package com.yongda.lc.support.support.web.tool;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * WEB工具类
 * 作者：xuchengen
 * 日期：2017/9/19 0019 - 9:52
 * 当你处于绝望中的时候，向你信任的人，爱的人倾诉，
 * 向家庭、朋友、警察、社区、社会寻求帮助。
 * 珍爱生命，快乐生活。
 */
public class WebTool {

    private static final Logger log = LoggerFactory.getLogger(WebTool.class);

    private static final String UTF_8 = "UTF-8";

    private static final String GBK = "GBK";

    /**
     * 线程安全的获取HttpServletRequest
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 线程安全的获取HttpSession
     *
     * @return HttpSession
     */
    public static HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }

    /**
     * 线程安全的获取HttpServletResponse
     *
     * @return HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取WebApplicationContext
     *
     * @return WebApplicationContext
     */
    public static WebApplicationContext getApplication() {
        return ContextLoader.getCurrentWebApplicationContext();
    }

    /**
     * 获取ServletContext
     *
     * @return ServletContext
     */
    public static ServletContext getServletContext() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getServletContext();
    }

    /**
     * 获取字符串参数
     *
     * @param name 参数名称
     * @return String
     */
    public static String getString(String name) {
        return getString(name, null);
    }

    /**
     * 获取字符串参数
     *
     * @param name         参数名称
     * @param defaultValue 默认值
     * @return String
     */
    public static String getString(String name, String defaultValue) {
        String resultStr = getRequest().getParameter(name);
        if (resultStr == null || "".equals(resultStr) || "null".equals(resultStr) || "undefined".equals(resultStr)) {
            return defaultValue;
        } else {
            return resultStr;
        }
    }

    /**
     * 获取请求中的参数值，如果参数值为null刚转为空字符串""
     *
     * @return Map
     */
    public static Map<String, Object> getParamMapNullStr(Map map) {
        Map<String, Object> parameters = new HashMap<>();
        Set keys = map.keySet();
        for (Object key : keys) {
            String value = getString(key.toString());
            if (value == null) {
                value = "";
            }
            parameters.put(key.toString(), value);
        }
        return parameters;
    }

    /**
     * 获取数值型参数
     *
     * @param name 参数名称
     * @return int
     */
    public static int getInt(String name) {
        return getInt(name, 0);
    }

    /**
     * 获取数值型参数
     *
     * @param name         参数名称
     * @param defaultValue 默认值
     * @return int
     */
    public static int getInt(String name, int defaultValue) {
        String resultStr = getRequest().getParameter(name);
        if (resultStr != null) {
            try {
                return Integer.parseInt(resultStr);
            } catch (Exception e) {
                log.error("参数转换错误:", e);
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * 获取浮点型参数
     *
     * @param name 参数名称
     * @return BigDecimal
     */
    public static BigDecimal getBigDecimal(String name) {
        return getBigDecimal(name, null);
    }

    /**
     * 获取浮点型参数
     *
     * @param name         参数名称
     * @param defaultValue 默认值
     * @return BigDecimal
     */
    public static BigDecimal getBigDecimal(String name, BigDecimal defaultValue) {
        String resultStr = getRequest().getParameter(name);
        if (resultStr != null) {
            try {
                return BigDecimal.valueOf(Double.parseDouble(resultStr));
            } catch (Exception e) {
                log.error("参数转换错误:", e);
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * 根据参数名从HttpRequest中获取String类型的参数值，无值则返回""
     *
     * @param key 参数名称
     * @return String
     */
    public static String getStringUrlDecodeUTF8(String key) {
        try {
            String string = getString(key.toString());
            if (StringUtil.isEmpty(string)) {
                return null;
            } else {
                return URLDecoder.decode(getString(key), UTF_8);
            }
        } catch (Exception e) {
            log.error("URL解码错误:", e);
            return "";
        }
    }

    /**
     * 根据参数名从HttpRequest中获取String类型的参数值，无值则返回""
     *
     * @param key 参数名称
     * @return String
     */
    public static String getStringUrlDecodeGBK(String key) {
        try {
            String string = getString(key.toString());
            if (StringUtil.isEmpty(string)) {
                return null;
            } else {
                return new String(getString(string).getBytes(GBK), UTF_8);
            }
        } catch (Exception e) {
            log.error("参数转码错误:", e);
            return "";
        }
    }

    /**
     * 获取客户端真实IP地址
     *
     * @return String
     */
    public static String getIpAddr() {
        String ipAddress;
        HttpServletRequest request = getRequest();
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("未知主机", e);
                }
                ipAddress = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 获取refererUrl-来路URL
     *
     * @return String
     */
    public static String getRefererUrl() {
        return getRequest().getHeader("referer");
    }

    /**
     * 读取RequestBody
     *
     * @return String
     * @throws IOException
     */
    public static String readRequest() throws IOException {
        HttpServletRequest request = getRequest();
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            while ((line = request.getReader().readLine()) != null) {
                sb.append(line);
            }
        } finally {
            request.getReader().close();
        }
        return sb.toString();
    }

    /**
     * 使用Response输出
     *
     * @param str 字符串
     */
    public static void write(String str) {
        HttpServletResponse response = getResponse();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(str);
        } catch (IOException e) {
            log.error("使用Response输出异常", e);
        } finally {
            out.close();
        }
    }

    /**
     * 是否Ajax请求
     *
     * @return boolean
     */
    public static boolean isAjax() {
        HttpServletRequest request = getRequest();
        if (!StrUtil.isBlank(request.getHeader("x-requested-with"))
                && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        }
        return false;
    }

    /**
     * 获取主机URL
     *
     * @return String
     */
    public static String getHostUrl() {
        HttpServletRequest request = getRequest();
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        String contextPath = request.getContextPath();
        int serverPort = request.getServerPort();

        StringBuilder sb = new StringBuilder();
        sb.append(scheme)
                .append("://")
                .append(serverName);

        if (80 != serverPort && 443 != serverPort) {
            sb.append(":").append(serverPort);
        }

        sb.append(contextPath);

        return sb.toString();
    }

}
