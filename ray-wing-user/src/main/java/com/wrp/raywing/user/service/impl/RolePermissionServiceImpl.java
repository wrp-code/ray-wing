package com.wrp.raywing.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wrp.raywing.user.mapper.RolePermissionMapper;
import com.wrp.raywing.user.entity.RolePermissionEntity;
import com.wrp.raywing.user.service.RolePermissionService;
import com.wrp.raywing.common.domain.PageParam;

/**
 * ${comments}
 *
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermissionEntity>
        implements RolePermissionService {

    @Override
    public IPage<RolePermissionEntity> page(PageParam<Object> pageParam) {
        IPage<RolePermissionEntity> page = new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
        return page(page, null);
    }
}