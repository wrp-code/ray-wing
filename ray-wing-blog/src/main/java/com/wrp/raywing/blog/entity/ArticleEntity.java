package com.wrp.raywing.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;



import com.wrp.raywing.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章
 * 
 * @author wrp
 * @since 2025-08-18 09:49:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "article", autoResultMap = true)
public class ArticleEntity extends BaseEntity {

	/**
	 * 文章
	 */

	private String title;
	/**
	 * 文章内容
	 */
	private String content;

}
