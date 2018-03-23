package com.yongda.lc.system.admin.configuration;

import com.yongda.lc.support.constant.GlobalConst;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

/**
 * Swagger接口文档配置
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2017/12/11-下午1:59
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Resource(name = "environment")
    private Environment environment;

    @Bean
    public Docket api() {
        //获取当前环境
        String profile = environment.getProperty("profile");
        if (profile.equals(GlobalConst.PROFILE_PROD)) {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .paths(PathSelectors.none())
                    .build();
        }

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yongda.lc.system.admin.web.controller"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("永达理财后台管理系统接口文档")
                .version("1.0")
                .build();
    }

}
