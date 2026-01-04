package com.wrp.raywing.common.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 接口在线文档配置
 * @author wrp
 * @since 2025年12月02日 14:10
 **/
@Configuration
public class Knite4jConfig {
    @Bean
    public GroupedOpenApi defaultApi() {
        return GroupedOpenApi.builder()
                .group("default") // 使用默认分组名
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI docsOpenApi() {
        return new OpenAPI()
                .info(new Info().title("ray-wing平台")
                        .description("ray-wing平台相关接口文档")
                        .contact(new Contact().name("wrp"))
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("ray-wing平台")
                        .url("https://gitee.com/wrp13547829236/ray-wing"));
    }
}
