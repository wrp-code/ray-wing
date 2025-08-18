package com.wrp.raywing.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wrp.raywing.user.mapper.PermissionMapper;
import com.wrp.raywing.user.entity.PermissionEntity;
import com.wrp.raywing.user.service.PermissionService;
import com.wrp.raywing.common.domain.PageParam;

/**
 * 角色
 *
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity>
        implements PermissionService {

    @Override
    public IPage<PermissionEntity> page(PageParam<Object> pageParam) {
        IPage<PermissionEntity> page = new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
        return page(page, null);
    }
}