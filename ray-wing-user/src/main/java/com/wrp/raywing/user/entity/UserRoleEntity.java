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
@TableName(value = "system.user_role", autoResultMap = true)
public class UserRoleEntity extends BaseEntity {

	/**
	 * $column.comments
	 */
	private Long userId;
	/**
	 * $column.comments
	 */
	private Long roleId;

}
