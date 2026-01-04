package com.wrp.raywing.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wrp.raywing.common.domain.Result;
import com.wrp.raywing.common.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.wrp.raywing.user.entity.FunctionPermissionEntity;
import com.wrp.raywing.user.service.FunctionPermissionService;
import java.util.List;
import com.wrp.raywing.common.domain.PageParam;


/**
 * ${comments}接口
 *
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
@RestController
@RequestMapping("function/permission")
public class FunctionPermissionController {
    @Autowired
    private FunctionPermissionService functionPermissionService;

    /**
     * 列表
     */
    @PostMapping("/list")
    public Result<IPage<FunctionPermissionEntity>> list(@RequestBody @Validated PageParam<Object> pageParam){
        IPage<FunctionPermissionEntity> page = functionPermissionService.page(pageParam);

        return ResultUtils.success(page);
    }

    /**
     * 信息
     */
    @GetMapping("{id}")
    public Result<FunctionPermissionEntity> info(@PathVariable("id") Long id){
		FunctionPermissionEntity functionPermission = functionPermissionService.getById(id);

        return ResultUtils.success(functionPermission);
    }

    /**
     * 保存
     */
    @PostMapping("save")
    public Result<Void> save(@RequestBody @Validated FunctionPermissionEntity functionPermission){
		functionPermissionService.save(functionPermission);

        return ResultUtils.success();
    }

    /**
     * 修改
     */
    @PutMapping("update")
    public Result<Void> update(@RequestBody @Validated FunctionPermissionEntity functionPermission){
		functionPermissionService.updateById(functionPermission);

        return ResultUtils.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("delete/{id}")
    public Result<Void> delete(@PathVariable("id") Long id){
		functionPermissionService.removeById(id);

        return ResultUtils.success();
    }

}
