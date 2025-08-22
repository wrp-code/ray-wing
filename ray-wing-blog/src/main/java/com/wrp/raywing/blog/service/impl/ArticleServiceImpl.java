package com.wrp.raywing.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wrp.raywing.blog.entity.CatalogArticleEntity;
import com.wrp.raywing.blog.entity.TitleInfo;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void upload(MultipartFile file, Long id) {
        String fileName = file.getOriginalFilename();
        String content;
        try {
            content = new String(file.getBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new BlogException("读取文件内容失败", e);
        }

        ArticleEntity entity = new ArticleEntity();
        if(getById(id) != null) {
            entity.setId(id);
        }
        entity.setContent(content);
        entity.setTitle(fileName.substring(0, fileName.lastIndexOf(".")));
        entity.setContentTable(extractTitles(content));
        this.saveOrUpdate(entity);
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

    @Override
    public void submit(ArticleEntity article) {
        article.setContentTable(extractTitles(article.getContent()));
        saveOrUpdate(article);
    }

    private static List<TitleInfo> extractTitles(String markdownText) {
        List<TitleInfo> titles = new ArrayList<>();

        // 匹配1-6级标题的正则表达式
        String regex = "^(#{1,6})\\s+(.*)$";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(removeCodeBlocks(markdownText));

        while (matcher.find()) {
            int level = matcher.group(1).length(); // 标题级别
            String title = matcher.group(2).trim(); // 标题内容
            titles.add(new TitleInfo(level, title));
        }

        return titles;
    }

    /**
     * 移除Markdown中的代码块内容
     */
    private static String removeCodeBlocks(String markdownText) {
        // 移除 ```代码块```
        String regex1 = "```[\\s\\S]*?```";
        String result = markdownText.replaceAll(regex1, "");

        // 移除 `行内代码`
        String regex2 = "`[^`]*`";
        result = result.replaceAll(regex2, "");

        // 移除缩进代码块（4个空格或1个制表符开头的行）
        String regex3 = "(?m)^( {4,}|\\t).*$";
        result = result.replaceAll(regex3, "");

        return result;
    }
}