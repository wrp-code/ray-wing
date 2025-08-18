package com.wrp.raywing.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wrp.raywing.common.domain.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.wrp.raywing.blog.entity.ArticleEntity;
import com.wrp.raywing.blog.service.ArticleService;
import com.wrp.raywing.common.domain.PageParam;


/**
 * 文章接口
 *
 * @author wrp
 * @since 2025-08-18 09:49:16
 */
@Tag(name="文章接口", description = "文章相关接口")
@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 列表
     */
    @Operation(summary = "分页查询")
    @PostMapping("/list")
    public Result<IPage<ArticleEntity>> list(@RequestBody @Validated PageParam<Object> pageParam){
        IPage<ArticleEntity> page = articleService.page(pageParam);

        return Result.success(page);
    }

    /**
     * 信息
     */
    @Operation(summary = "根据id获取详情")
    @GetMapping("{id}")
    public Result<ArticleEntity> info(@PathVariable("id") Long id){
		ArticleEntity article = articleService.getById(id);

        return Result.success(article);
    }

    /**
     * 保存
     */
    @Operation(summary = "保存文章")
    @PostMapping("save")
    public Result<Void> save(@RequestBody @Validated ArticleEntity article){
		articleService.save(article);

        return Result.success();
    }

    /**
     * 修改
     */
    @Operation(summary = "更新文章")
    @PutMapping("update")
    public Result<Void> update(@RequestBody @Validated ArticleEntity article){
		articleService.updateById(article);

        return Result.success();
    }


}
