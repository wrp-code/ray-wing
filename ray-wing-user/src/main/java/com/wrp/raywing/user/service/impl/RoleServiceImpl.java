package com.wrp.raywing.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wrp.raywing.user.mapper.RoleMapper;
import com.wrp.raywing.user.entity.RoleEntity;
import com.wrp.raywing.user.service.RoleService;
import com.wrp.raywing.common.domain.PageParam;

/**
 * 功能
 *
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity>
        implements RoleService {

    @Override
    public IPage<RoleEntity> page(PageParam<Object> pageParam) {
        IPage<RoleEntity> page = new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
        return page(page, null);
    }
}