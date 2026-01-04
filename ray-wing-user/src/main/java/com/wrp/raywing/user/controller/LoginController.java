package com.wrp.raywing.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wrp
 * @since 2025年09月05日 14:52
 **/
@Tag(name = "登录模块")
@RestController
public class LoginController {

    @Operation(summary = "获取简单验证码")
    @RequestMapping
    public void captcha() {

    }
}
