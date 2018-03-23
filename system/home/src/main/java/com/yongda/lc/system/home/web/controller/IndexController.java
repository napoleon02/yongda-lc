package com.yongda.lc.system.home.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yongda.lc.support.support.web.response.ResponseWrapper;

/**
 * 首页控制器
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/1-上午9:32
 */
@Controller
public class IndexController {

    @GetMapping(value = {"/", "/index"})
    @ResponseBody
    public ResponseWrapper index() {
        return ResponseWrapper.ok();
    }

}
