package com.wrp.raywing.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;



import com.wrp.raywing.common.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户
 * 
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "system.sys_user", autoResultMap = true)
public class SysUserEntity extends BaseEntity {

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
