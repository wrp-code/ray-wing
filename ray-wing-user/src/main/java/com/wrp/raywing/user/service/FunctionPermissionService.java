package com.wrp.raywing.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wrp.raywing.user.entity.FunctionPermissionEntity;

import com.wrp.raywing.common.domain.PageParam;

/**
 * ${comments}
 *
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
public interface FunctionPermissionService extends IService<FunctionPermissionEntity> {

    IPage<FunctionPermissionEntity> page(PageParam<Object> pageParam);
}

