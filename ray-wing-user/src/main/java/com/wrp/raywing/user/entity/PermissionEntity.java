package com.wrp.raywing.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;



import com.wrp.raywing.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色
 * 
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "system.permission", autoResultMap = true)
public class PermissionEntity extends BaseEntity {

	/**
	 * 权限名称
	 */
	private String permissionName;
	/**
	 * 权限描述符
	 */
	private String permissionIdentify;
	/**
	 * 描述
	 */
	private String description;

}
