package com.wrp.raywing.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wrp.raywing.blog.entity.CatalogArticleEntity;
import com.wrp.raywing.blog.service.ArticleService;
import com.wrp.raywing.blog.service.CatalogArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wrp.raywing.blog.mapper.CatalogMapper;
import com.wrp.raywing.blog.entity.CatalogEntity;
import com.wrp.raywing.blog.service.CatalogService;
import com.wrp.raywing.common.domain.PageParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 目录分类
 *
 * @author wrp
 * @since 2025-08-18 13:59:29
 */
@Service("catalogService")
@RequiredArgsConstructor
public class CatalogServiceImpl extends ServiceImpl<CatalogMapper, CatalogEntity>
        implements CatalogService {

    private final CatalogArticleService catalogArticleService;
    private final ArticleService articleService;

    @Override
    public IPage<CatalogEntity> page(PageParam<Object> pageParam) {
        IPage<CatalogEntity> page = new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
        return page(page, null);
    }

    @Override
    public List<CatalogEntity> tree() {
        List<CatalogEntity> list = list();
        Map<Long, CatalogEntity> map = new HashMap<>();
        for (CatalogEntity catalogEntity : list) {
            catalogEntity.setChild(new ArrayList<>());
            map.put(catalogEntity.getId(), catalogEntity);
        }

        list.forEach(catalogEntity -> {
            if(catalogEntity.getParentId() != null && map.containsKey(catalogEntity.getParentId())) {
                map.get(catalogEntity.getParentId()).getChild().add(catalogEntity);
            }
        });
        return list.stream().filter(entity-> entity.getParentId() == null).toList();
    }

    @Override
    public void bind(CatalogArticleEntity catalogArticleEntity) {
        CatalogEntity entity;
        if((entity = getById(catalogArticleEntity.getCatalogId())) != null &&
                articleService.getById(catalogArticleEntity.getArticleId()) != null) {
            catalogArticleService.save(catalogArticleEntity);
            entity.setArticleCount(entity.getArticleCount() + 1);
            updateById(entity);
        }
    }
}