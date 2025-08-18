package com.wrp.raywing.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wrp.raywing.blog.entity.CatalogArticleEntity;
import com.wrp.raywing.blog.exception.BlogException;
import com.wrp.raywing.blog.service.CatalogArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wrp.raywing.blog.mapper.ArticleMapper;
import com.wrp.raywing.blog.entity.ArticleEntity;
import com.wrp.raywing.blog.service.ArticleService;
import com.wrp.raywing.common.domain.PageParam;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * 文章
 *
 * @author wrp
 * @since 2025-08-18 09:49:16
 */
@Service("articleService")
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleEntity>
        implements ArticleService {

    private final CatalogArticleService catalogArticleService;

    @Override
    public IPage<ArticleEntity> page(PageParam<Object> pageParam) {
        IPage<ArticleEntity> page = new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
        return page(page, null);
    }

    @Override
    public void upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String content;
        try {
            content = new String(file.getBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new BlogException("读取文件内容失败", e);
        }

        ArticleEntity entity = new ArticleEntity();
        entity.setContent(content);
        entity.setTitle(fileName.substring(0, fileName.lastIndexOf(".")));
        this.save(entity);
    }

    @Override
    public List<ArticleEntity> listByCatalogId(Long catalogId) {
        List<CatalogArticleEntity> catalogArticleEntityList = catalogArticleService.listByCatalogId(catalogId);
        if(CollectionUtils.isEmpty(catalogArticleEntityList)){
            return Collections.emptyList();
        }

        return list(new LambdaQueryWrapper<ArticleEntity>()
                .select(ArticleEntity::getTitle, ArticleEntity::getCreateTime, ArticleEntity::getId)
                .in(ArticleEntity::getId,
                        catalogArticleEntityList.stream().map(CatalogArticleEntity::getArticleId).toList()));
    }
}