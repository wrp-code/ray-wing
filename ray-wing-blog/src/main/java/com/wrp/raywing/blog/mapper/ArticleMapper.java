package com.wrp.raywing.blog.mapper;

import com.wrp.raywing.blog.entity.ArticleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章
 * 
 * @author wrp
 * @since 2025-08-18 09:49:16
 */
@Mapper
public interface ArticleMapper extends BaseMapper<ArticleEntity> {
	
}
