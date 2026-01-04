package com.wrp.raywing.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wrp.raywing.common.domain.Result;
import com.wrp.raywing.common.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.wrp.raywing.user.entity.UserRoleEntity;
import com.wrp.raywing.user.service.UserRoleService;
import java.util.List;
import com.wrp.raywing.common.domain.PageParam;


/**
 * ${comments}接口
 *
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
@RestController
@RequestMapping("user/role")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 列表
     */
    @PostMapping("/list")
    public Result<IPage<UserRoleEntity>> list(@RequestBody @Validated PageParam<Object> pageParam){
        IPage<UserRoleEntity> page = userRoleService.page(pageParam);

        return ResultUtils.success(page);
    }

    /**
     * 信息
     */
    @GetMapping("{id}")
    public Result<UserRoleEntity> info(@PathVariable("id") Long id){
		UserRoleEntity userRole = userRoleService.getById(id);

        return ResultUtils.success(userRole);
    }

    /**
     * 保存
     */
    @PostMapping("save")
    public Result<Void> save(@RequestBody @Validated UserRoleEntity userRole){
		userRoleService.save(userRole);

        return ResultUtils.success();
    }

    /**
     * 修改
     */
    @PutMapping("update")
    public Result<Void> update(@RequestBody @Validated UserRoleEntity userRole){
		userRoleService.updateById(userRole);

        return ResultUtils.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("delete/{id}")
    public Result<Void> delete(@PathVariable("id") Long id){
		userRoleService.removeById(id);

        return ResultUtils.success();
    }

}
