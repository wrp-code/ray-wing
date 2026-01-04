package com.wrp.raywing.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wrp.raywing.user.controller.param.RegisterUser;
import com.wrp.raywing.user.entity.SysUserEntity;

import com.wrp.raywing.common.domain.PageParam;

/**
 * 用户
 *
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
public interface SysUserService extends IService<SysUserEntity> {

    IPage<SysUserEntity> page(PageParam<Object> pageParam);

    Long register(RegisterUser registerUser);
}

