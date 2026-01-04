package com.wrp.raywing.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wrp.raywing.common.domain.Result;
import com.wrp.raywing.common.util.ResultUtils;
import com.wrp.raywing.user.controller.param.RegisterUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.wrp.raywing.user.entity.SysUserEntity;
import com.wrp.raywing.user.service.SysUserService;
import com.wrp.raywing.common.domain.PageParam;


/**
 * 用户接口
 *
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     */
    @Operation(summary = "用户列表")
    @PostMapping("/list")
    public Result<IPage<SysUserEntity>> list(@RequestBody @Validated PageParam<Object> pageParam){
        IPage<SysUserEntity> page = sysUserService.page(pageParam);

        return ResultUtils.success(page);
    }

    /**
     * 信息
     */
    @Operation(summary = "用户详细信息")
    @GetMapping("{id}")
    public Result<SysUserEntity> info(@PathVariable("id") Long id){
		SysUserEntity sysUser = sysUserService.getById(id);

        return ResultUtils.success(sysUser);
    }

    /**
     * 保存
     */
    @Operation(summary = "注册用户")
    @PostMapping()
    public Result<Long> register(@RequestBody @Validated RegisterUser registerUser){
        Long id = sysUserService.register(registerUser);

        return ResultUtils.success(id);
    }

    /**
     * 修改
     */
    @Operation(summary = "修改用户")
    @PutMapping("update")
    public Result<Void> update(@RequestBody @Validated SysUserEntity sysUser){
		sysUserService.updateById(sysUser);

        return ResultUtils.success();
    }

    /**
     * 删除
     */
    @Operation(summary = "删除用户")
    @DeleteMapping("{id}")
    public Result<Void> delete(@PathVariable("id") Long id){
		sysUserService.removeById(id);

        return ResultUtils.success();
    }

}
