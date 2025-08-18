package com.wrp.raywing.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wrp.raywing.user.entity.RoleEntity;

import com.wrp.raywing.common.domain.PageParam;

/**
 * 功能
 *
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
public interface RoleService extends IService<RoleEntity> {

    IPage<RoleEntity> page(PageParam<Object> pageParam);
}

