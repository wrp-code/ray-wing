package com.wrp.raywing.blog.controller;

import com.wrp.raywing.blog.entity.CatalogArticleEntity;
import com.wrp.raywing.blog.entity.CatalogEntity;
import com.wrp.raywing.blog.service.CatalogService;
import com.wrp.raywing.common.domain.Result;
import com.wrp.raywing.common.util.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章目录管理接口
 * @author wrp
 * @since 2025年08月18日 14:00
 **/
@RestController
@RequestMapping("catalog")
@Tag(name = "文章目录管理接口")
@AllArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    /**
     * 查询文章目录树状结构
     */
    @Operation(summary = "查询文章目录树状结构")
    @GetMapping("tree")
    public Result<List<CatalogEntity>> list() {
        return ResultUtils.success(catalogService.tree());
    }

    @Operation(summary = "查询书籍列表")
    @GetMapping("books")
    public Result<List<CatalogEntity>> books(@RequestParam(value = "id", required = false) Long id) {
        return ResultUtils.success(catalogService.books(id));
    }

    /**
     * 添加目录
     */
    @Operation(summary = "添加目录")
    @PostMapping("add")
    public Result<Long> add(@RequestBody CatalogEntity catalog) {
        catalogService.save(catalog);
        return ResultUtils.success(catalog.getId());
    }

    /**
     * 将文章放入指定目录
     */
    @Operation(summary = "文章放入指定目录")
    @PostMapping("bind/article")
    public Result<Void> bind(@RequestBody CatalogArticleEntity catalogArticleEntity) {
        catalogService.bind(catalogArticleEntity);
        return ResultUtils.success();
    }
}
