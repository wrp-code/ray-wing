package com.wrp.raywing.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wrp.raywing.user.entity.PermissionEntity;

import com.wrp.raywing.common.domain.PageParam;

/**
 * 角色
 *
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
public interface PermissionService extends IService<PermissionEntity> {

    IPage<PermissionEntity> page(PageParam<Object> pageParam);
}

