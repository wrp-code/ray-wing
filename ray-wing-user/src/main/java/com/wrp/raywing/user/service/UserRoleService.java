package com.wrp.raywing.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wrp.raywing.user.entity.UserRoleEntity;

import com.wrp.raywing.common.domain.PageParam;

/**
 * ${comments}
 *
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
public interface UserRoleService extends IService<UserRoleEntity> {

    IPage<UserRoleEntity> page(PageParam<Object> pageParam);
}

