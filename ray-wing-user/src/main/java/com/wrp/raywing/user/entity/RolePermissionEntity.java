package com.wrp.raywing.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;



import com.wrp.raywing.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ${comments}
 * 
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "system.role_permission", autoResultMap = true)
public class RolePermissionEntity extends BaseEntity {

	/**
	 * $column.comments
	 */
	private Long roleId;
	/**
	 * $column.comments
	 */
	private Long permissionId;

}
