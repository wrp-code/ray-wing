package com.wrp.raywing.blog.controller;

import com.wrp.raywing.common.annotation.UpdateGroup;
import com.wrp.raywing.common.domain.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.wrp.raywing.blog.entity.ArticleEntity;
import com.wrp.raywing.blog.service.ArticleService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


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
     * 根据目录id获取文章列表
     */
    @Operation(summary = "根据目录id获取文章列表")
    @GetMapping("/list")
    public Result<List<ArticleEntity>> list(@RequestParam("catalogId") Long catalogId){

        return Result.success(articleService.listByCatalogId(catalogId));
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
    public Result<Long> save(@RequestBody @Validated ArticleEntity article){
        return Result.success(articleService.submit(article));
    }

    /**
     * 修改
     */
    @Operation(summary = "更新文章")
    @PutMapping("update")
    public Result<Long> update(@RequestBody @Validated(UpdateGroup.class) ArticleEntity article){
        return Result.success(articleService.submit(article));
    }

    /**
     * 上传markdown格式文章
     */
    @Operation(summary = "上传markdown格式文章", description = "上传markdown文件来创建文章")
    @PostMapping("upload")
    public Result<Long> upload(@RequestPart("file") MultipartFile file, @RequestParam(value = "id", required = false) Long id){
        return Result.success(articleService.upload(file, id));
    }

}
