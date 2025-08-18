package com.wrp.raywing.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wrp.raywing.user.mapper.FunctionMapper;
import com.wrp.raywing.user.entity.FunctionEntity;
import com.wrp.raywing.user.service.FunctionService;
import com.wrp.raywing.common.domain.PageParam;

/**
 * ${comments}
 *
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
@Service("functionService")
public class FunctionServiceImpl extends ServiceImpl<FunctionMapper, FunctionEntity>
        implements FunctionService {

    @Override
    public IPage<FunctionEntity> page(PageParam<Object> pageParam) {
        IPage<FunctionEntity> page = new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
        return page(page, null);
    }
}