package com.wrp.raywing.store.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wrp.raywing.store.entity.FilesEntity;

import com.wrp.raywing.common.domain.PageParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 存储表
 *
 * @author wrp
 * @since 2025-08-25 12:33:54
 */
public interface FilesService extends IService<FilesEntity> {

    IPage<FilesEntity> page(PageParam<Object> pageParam);

    Long upload(MultipartFile file);
}

