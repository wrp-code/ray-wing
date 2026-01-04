package com.wrp.raywing.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wrp.raywing.common.domain.Result;
import com.wrp.raywing.common.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.wrp.raywing.user.entity.RolePermissionEntity;
import com.wrp.raywing.user.service.RolePermissionService;
import java.util.List;
import com.wrp.raywing.common.domain.PageParam;


/**
 * ${comments}接口
 *
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
@RestController
@RequestMapping("role/permission")
public class RolePermissionController {
    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 列表
     */
    @PostMapping("/list")
    public Result<IPage<RolePermissionEntity>> list(@RequestBody @Validated PageParam<Object> pageParam){
        IPage<RolePermissionEntity> page = rolePermissionService.page(pageParam);

        return ResultUtils.success(page);
    }

    /**
     * 信息
     */
    @GetMapping("{id}")
    public Result<RolePermissionEntity> info(@PathVariable("id") Long id){
		RolePermissionEntity rolePermission = rolePermissionService.getById(id);

        return ResultUtils.success(rolePermission);
    }

    /**
     * 保存
     */
    @PostMapping("save")
    public Result<Void> save(@RequestBody @Validated RolePermissionEntity rolePermission){
		rolePermissionService.save(rolePermission);

        return ResultUtils.success();
    }

    /**
     * 修改
     */
    @PutMapping("update")
    public Result<Void> update(@RequestBody @Validated RolePermissionEntity rolePermission){
		rolePermissionService.updateById(rolePermission);

        return ResultUtils.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("delete/{id}")
    public Result<Void> delete(@PathVariable("id") Long id){
		rolePermissionService.removeById(id);

        return ResultUtils.success();
    }

}
