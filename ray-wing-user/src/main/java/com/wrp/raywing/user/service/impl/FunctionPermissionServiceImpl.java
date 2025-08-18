package com.wrp.raywing.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wrp.raywing.user.mapper.FunctionPermissionMapper;
import com.wrp.raywing.user.entity.FunctionPermissionEntity;
import com.wrp.raywing.user.service.FunctionPermissionService;
import com.wrp.raywing.common.domain.PageParam;

/**
 * ${comments}
 *
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
@Service("functionPermissionService")
public class FunctionPermissionServiceImpl extends ServiceImpl<FunctionPermissionMapper, FunctionPermissionEntity>
        implements FunctionPermissionService {

    @Override
    public IPage<FunctionPermissionEntity> page(PageParam<Object> pageParam) {
        IPage<FunctionPermissionEntity> page = new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
        return page(page, null);
    }
}