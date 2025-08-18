package com.wrp.raywing.blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wrp
 * @since 2025年08月18日 10:22
 **/
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(getApiInfo())
                ;
    }

    private Info getApiInfo() {
        return new Info()
                // 配置文档标题
                .title("个人博客在线文档")
                // 配置文档描述
                .description("基于SpringDoc的OpenAPI 3.0")
                // 配置作者信息
                .contact(new Contact().name("wrp").email("wrphaha9@gmail.com"))
                // 配置License许可证信息
//                .license(new License().name("Apache 2.0").url("https://www.xiezhrspace.cn"))
                // 概述信息
                .summary("SpringBoot3 个人博客")
//                .termsOfService("https://www.xiezhrspace.cn")
                // 配置版本号
                .version("2.0");
    }
}
