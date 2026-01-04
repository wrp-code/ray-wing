package com.wrp.raywing.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wrp.raywing.user.controller.param.RegisterUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wrp.raywing.user.mapper.SysUserMapper;
import com.wrp.raywing.user.entity.SysUserEntity;
import com.wrp.raywing.user.service.SysUserService;
import com.wrp.raywing.common.domain.PageParam;

/**
 * 用户
 *
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
@Service("sysUserService")
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity>
        implements SysUserService {


    @Override
    public IPage<SysUserEntity> page(PageParam<Object> pageParam) {
        IPage<SysUserEntity> page = new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
        return page(page, null);
    }

    @Override
    public Long register(RegisterUser registerUser) {
        SysUserEntity user = new SysUserEntity();
        BeanUtils.copyProperties(registerUser, user);
        return 0L;
    }
}