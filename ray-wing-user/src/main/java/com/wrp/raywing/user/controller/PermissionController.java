package com.wrp.raywing.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wrp.raywing.common.domain.Result;
import com.wrp.raywing.common.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.wrp.raywing.user.entity.PermissionEntity;
import com.wrp.raywing.user.service.PermissionService;
import java.util.List;
import com.wrp.raywing.common.domain.PageParam;


/**
 * 权限接口
 *
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
@RestController
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 列表
     */
    @PostMapping("/list")
    public Result<IPage<PermissionEntity>> list(@RequestBody @Validated PageParam<Object> pageParam){
        IPage<PermissionEntity> page = permissionService.page(pageParam);

        return ResultUtils.success(page);
    }

    /**
     * 信息
     */
    @GetMapping("{id}")
    public Result<PermissionEntity> info(@PathVariable("id") Long id){
		PermissionEntity permission = permissionService.getById(id);

        return ResultUtils.success(permission);
    }

    /**
     * 保存
     */
    @PostMapping("save")
    public Result<Void> save(@RequestBody @Validated PermissionEntity permission){
		permissionService.save(permission);

        return ResultUtils.success();
    }

    /**
     * 修改
     */
    @PutMapping("update")
    public Result<Void> update(@RequestBody @Validated PermissionEntity permission){
		permissionService.updateById(permission);

        return ResultUtils.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("delete/{id}")
    public Result<Void> delete(@PathVariable("id") Long id){
		permissionService.removeById(id);

        return ResultUtils.success();
    }

}
