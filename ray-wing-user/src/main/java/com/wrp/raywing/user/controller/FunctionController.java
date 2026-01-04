package com.wrp.raywing.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wrp.raywing.common.domain.Result;
import com.wrp.raywing.common.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.wrp.raywing.user.entity.FunctionEntity;
import com.wrp.raywing.user.service.FunctionService;
import java.util.List;
import com.wrp.raywing.common.domain.PageParam;


/**
 * ${comments}接口
 *
 * @author wrp
 * @since 2025-08-18 11:24:19
 */
@RestController
@RequestMapping("function")
public class FunctionController {
    @Autowired
    private FunctionService functionService;

    /**
     * 列表
     */
    @PostMapping("/list")
    public Result<IPage<FunctionEntity>> list(@RequestBody @Validated PageParam<Object> pageParam){
        IPage<FunctionEntity> page = functionService.page(pageParam);

        return ResultUtils.success(page);
    }

    /**
     * 信息
     */
    @GetMapping("{id}")
    public Result<FunctionEntity> info(@PathVariable("id") Long id){
		FunctionEntity function = functionService.getById(id);

        return ResultUtils.success(function);
    }

    /**
     * 保存
     */
    @PostMapping("save")
    public Result<Void> save(@RequestBody @Validated FunctionEntity function){
		functionService.save(function);

        return ResultUtils.success();
    }

    /**
     * 修改
     */
    @PutMapping("update")
    public Result<Void> update(@RequestBody @Validated FunctionEntity function){
		functionService.updateById(function);

        return ResultUtils.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("delete/{id}")
    public Result<Void> delete(@PathVariable("id") Long id){
		functionService.removeById(id);

        return ResultUtils.success();
    }

}
