package com.wrp.raywing.blog.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wrp
 * @since 2025年08月22日 14:07
 **/
@Configuration
public class Knite4jConfig {

    @Bean
    public GroupedOpenApi defaultApi() {
        return GroupedOpenApi.builder()
                .group("默认") // 使用默认分组名
                .pathsToMatch("/**")
                .packagesToScan("com.wrp.raywing")
                .build();
    }

    @Bean
    public OpenAPI docsOpenApi() {
        return new OpenAPI()
                .info(new Info().title("RayWing's 个人博客网站接口文档")
                        .description("RayWing's 个人博客网站，基于SpringBoot3 + Vue3")
                        .contact(new Contact().name("wrp"))
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("RayWing's 个人博客网站")
                        .url("xxx"));
    }
}
