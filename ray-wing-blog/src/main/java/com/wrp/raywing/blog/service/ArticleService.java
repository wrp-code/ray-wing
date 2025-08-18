package com.wrp.raywing.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wrp.raywing.blog.entity.ArticleEntity;

import com.wrp.raywing.common.domain.PageParam;

/**
 * 文章
 *
 * @author wrp
 * @since 2025-08-18 09:49:16
 */
public interface ArticleService extends IService<ArticleEntity> {

    IPage<ArticleEntity> page(PageParam<Object> pageParam);
}

