package com.wrp.raywing.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wrp.raywing.common.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.wrp.raywing.user.entity.RoleEntity;
import com.wrp.raywing.user.service.RoleService;
import java.util.List;
import com.wrp.raywing.common.domain.PageParam;


/**
 * 功能接口
 *
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 列表
     */
    @PostMapping("/list")
    public Result<IPage<RoleEntity>> list(@RequestBody @Validated PageParam<Object> pageParam){
        IPage<RoleEntity> page = roleService.page(pageParam);

        return Result.success(page);
    }

    /**
     * 信息
     */
    @GetMapping("{id}")
    public Result<RoleEntity> info(@PathVariable("id") Long id){
		RoleEntity role = roleService.getById(id);

        return Result.success(role);
    }

    /**
     * 保存
     */
    @PostMapping("save")
    public Result<Void> save(@RequestBody @Validated RoleEntity role){
		roleService.save(role);

        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("update")
    public Result<Void> update(@RequestBody @Validated RoleEntity role){
		roleService.updateById(role);

        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("delete/{id}")
    public Result<Void> delete(@PathVariable("id") Long id){
		roleService.removeById(id);

        return Result.success();
    }

}
