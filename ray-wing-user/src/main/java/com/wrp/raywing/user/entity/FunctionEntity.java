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
@TableName(value = "system.function", autoResultMap = true)
public class FunctionEntity extends BaseEntity {

	/**
	 * 功能
	 */
	private String name;
	/**
	 * 父级id
	 */
	private Long parentId;
	/**
	 * 描述
	 */
	private String description;

}
