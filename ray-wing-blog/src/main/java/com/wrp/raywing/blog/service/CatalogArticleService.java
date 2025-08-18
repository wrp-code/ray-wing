package com.wrp.raywing.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wrp.raywing.blog.entity.CatalogArticleEntity;

import com.wrp.raywing.common.domain.PageParam;

import java.util.List;

/**
 * 目录分类关联文章
 *
 * @author wrp
 * @since 2025-08-18 13:59:29
 */
public interface CatalogArticleService extends IService<CatalogArticleEntity> {

    IPage<CatalogArticleEntity> page(PageParam<Object> pageParam);

    List<CatalogArticleEntity> listByCatalogId(Long catalogId);
}

