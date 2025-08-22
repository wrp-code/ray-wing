package com.wrp.raywing.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wrp.raywing.blog.dict.CatalogType;
import com.wrp.raywing.blog.entity.CatalogArticleEntity;
import com.wrp.raywing.blog.entity.CatalogEntity;

import com.wrp.raywing.common.domain.PageParam;

import java.util.List;

/**
 * 目录分类
 *
 * @author wrp
 * @since 2025-08-18 13:59:29
 */
public interface CatalogService extends IService<CatalogEntity> {

    IPage<CatalogEntity> page(PageParam<Object> pageParam);

    List<CatalogEntity> tree();

    void bind(CatalogArticleEntity catalogArticleEntity);

    List<CatalogEntity> books(Long id);
}

