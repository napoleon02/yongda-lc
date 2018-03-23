package com.yongda.lc.support.support.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 系统关闭控制器
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/8-下午2:16
 */
@Controller(value = "/system")
public class ShutdownController {

    @GetMapping(value = "/shutdown")
    public void shutdown() {
        System.exit(1);
    }

}
