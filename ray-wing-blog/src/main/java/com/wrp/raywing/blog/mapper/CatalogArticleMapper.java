package com.wrp.raywing.blog.mapper;

import com.wrp.raywing.blog.entity.CatalogArticleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 目录分类关联文章
 * 
 * @author wrp
 * @since 2025-08-18 13:59:29
 */
@Mapper
public interface CatalogArticleMapper extends BaseMapper<CatalogArticleEntity> {
	
}
