package com.yongda.lc.system.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页控制器
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/1-上午9:32
 */
@Controller
public class IndexController {

    /**
     * 首页
     */
    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "redirect:index.html";
    }

}
