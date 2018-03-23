package com.yongda.lc.system.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 系统启动类
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/1-上午9:08
 */
@MapperScan(value = "com.yongda.libra.dal")
@SpringBootApplication(scanBasePackages = {"com.yongda.lc"})
@EnableScheduling
@EnableTransactionManagement
@ServletComponentScan
public class Launcher {

    /**
     * 系统启动方法
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

}
