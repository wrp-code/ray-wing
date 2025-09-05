package com.wrp.raywing.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wrp.raywing.common.domain.BaseEntity;
import com.wrp.raywing.blog.typehandler.TitleInfoListTypeHandler;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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
	@NotEmpty
	private String title;
	/**
	 * 文章内容
	 */
	@NotEmpty
	private String content;

	/**
	 * 带锚点的目录列表
	 */
	@TableField(typeHandler = TitleInfoListTypeHandler.class)
	private List<TitleInfo> contentTable;

}
