package com.wrp.raywing.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;



import com.wrp.raywing.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能
 * 
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "system.role", autoResultMap = true)
public class RoleEntity extends BaseEntity {

	/**
	 * 角色
	 */
	private String roleName;
	/**
	 * 描述
	 */
	private String description;

}
