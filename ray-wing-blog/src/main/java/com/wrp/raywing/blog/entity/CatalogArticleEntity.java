package com.wrp.raywing.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;



import com.wrp.raywing.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 目录分类关联文章
 * 
 * @author wrp
 * @since 2025-08-18 13:59:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "public.catalog_article", autoResultMap = true)
public class CatalogArticleEntity extends BaseEntity {

	/**
	 * $column.comments
	 */
	private Long catalogId;
	/**
	 * $column.comments
	 */
	private Long articleId;

}
