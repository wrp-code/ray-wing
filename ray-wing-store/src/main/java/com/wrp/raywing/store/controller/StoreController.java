package com.wrp.raywing.store.controller;

import com.wrp.raywing.common.domain.Result;
import com.wrp.raywing.common.util.ResultUtils;
import com.wrp.raywing.store.service.FilesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wrp
 * @since 2025年08月25日 12:28
 **/
@RestController
@RequestMapping("store")
@AllArgsConstructor
@Tag(name = "存储服务接口")
public class StoreController {

    private final FilesService filesService;

    @Operation(summary = "上传单文件")
    @PostMapping("upload")
    public Result<Long> upload(@RequestPart("file") MultipartFile file) {

        return ResultUtils.success(filesService.upload(file));
    }
}
