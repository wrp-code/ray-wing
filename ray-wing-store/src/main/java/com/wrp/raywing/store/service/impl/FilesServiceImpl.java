package com.wrp.raywing.store.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wrp.raywing.common.properties.SystemProperties;
import com.wrp.raywing.store.exception.StoreException;
import io.swagger.v3.oas.models.Paths;
import lombok.AllArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wrp.raywing.store.mapper.FilesMapper;
import com.wrp.raywing.store.entity.FilesEntity;
import com.wrp.raywing.store.service.FilesService;
import com.wrp.raywing.common.domain.PageParam;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.UUID;

/**
 * 存储表
 *
 * @author wrp
 * @since 2025-08-25 12:33:54
 */
@Service("filesService")
@AllArgsConstructor
public class FilesServiceImpl extends ServiceImpl<FilesMapper, FilesEntity>
        implements FilesService {

    private final SystemProperties systemProperties;

    @Override
    public IPage<FilesEntity> page(PageParam<Object> pageParam) {
        IPage<FilesEntity> page = new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
        return page(page, null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long upload(MultipartFile file) {
        String contentType = file.getContentType();
        if(contentType.equals("image/jpeg") || contentType.equals("image/png")) {
            throw new StoreException("仅支持jpg或png");
        }

        FilesEntity entity = new FilesEntity();
        entity.setFileName(file.getOriginalFilename());
        entity.setType(contentType);
        entity.setSize(file.getSize());
        LocalDate now = LocalDate.now();
        Path relativePath = Path.of("" + now.getYear(), "" + now.getMonth(), "" + now.getDayOfMonth());
        String uuid = UUID.randomUUID().toString();
        Path path = relativePath.resolve(uuid);
        entity.setUrl(path.toString());
        save(entity);

        writeFile(file, relativePath, path);
        return entity.getId();
    }

    private void writeFile(MultipartFile file, Path relativePath, Path path) {
        Path destinationPath = systemProperties.getBasePath().resolve(relativePath);
        if(!Files.exists(destinationPath)) {
            try {
                Files.createDirectories(destinationPath);
                file.transferTo(path);
            } catch (IOException e) {
                throw new StoreException("磁盘异常", e);
            }
        }
    }
}