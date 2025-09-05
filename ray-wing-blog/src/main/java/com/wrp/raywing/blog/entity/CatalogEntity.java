package com.wrp.raywing.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;


import com.wrp.raywing.blog.dict.CatalogType;
import com.wrp.raywing.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 目录分类
 * 
 * @author wrp
 * @since 2025-08-18 13:59:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "public.catalog", autoResultMap = true)
public class CatalogEntity extends BaseEntity {

	/**
	 * 目录名称
	 */
	private String name;
	/**
	 * 父级目录ID
	 */
	private Long parentId;

	/**
	 * 目录分类
	 */
	private CatalogType catalogType;
	/**
	 * 文章数量
	 */
	private Integer articleCount;
	/**
	 * 描述
	 */
	private String description;

	/**
	 * 下级目录
	 */
	@TableField(exist = false)
	private List<CatalogEntity> child;

}
