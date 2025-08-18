package com.wrp.raywing.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wrp.raywing.blog.mapper.CatalogArticleMapper;
import com.wrp.raywing.blog.entity.CatalogArticleEntity;
import com.wrp.raywing.blog.service.CatalogArticleService;
import com.wrp.raywing.common.domain.PageParam;

import java.util.List;

/**
 * 目录分类关联文章
 *
 * @author wrp
 * @since 2025-08-18 13:59:29
 */
@Service("catalogArticleService")
public class CatalogArticleServiceImpl extends ServiceImpl<CatalogArticleMapper, CatalogArticleEntity>
        implements CatalogArticleService {

    @Override
    public IPage<CatalogArticleEntity> page(PageParam<Object> pageParam) {
        IPage<CatalogArticleEntity> page = new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
        return page(page, null);
    }

    @Override
    public List<CatalogArticleEntity> listByCatalogId(Long catalogId) {
        return list(new LambdaQueryWrapper<CatalogArticleEntity>()
                .eq(CatalogArticleEntity::getCatalogId, catalogId));
    }
}