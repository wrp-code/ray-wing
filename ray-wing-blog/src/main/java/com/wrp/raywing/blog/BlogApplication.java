package com.wrp.raywing.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wrp
 * @since 2025年08月18日 9:27
 **/
@MapperScan("com.wrp.raywing.**.mapper")
@SpringBootApplication(scanBasePackages = "com.wrp.raywing")
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
