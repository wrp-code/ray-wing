package com.wrp.raywing.user.controller.param;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author wrp
 * @since 2026年01月04日 11:05
 **/
public class RegisterUser {

    /**
     * 用户名
     */
    @Schema(name = "用户名")
    private String username;
    /**
     * 密码
     */
    @Schema(name = "密码")
    private String password;
    /**
     * 真实姓名
     */
    @Schema(name = "真实姓名")
    private String fullName;
    /**
     * 手机号
     */
    @Schema(name = "手机号")
    private String phone;
    /**
     * 邮箱
     */
    @Schema(name = "邮箱")
    private String email;
    /**
     * 头像
     */
    @Schema(name = "头像")
    private String avatar;
}
