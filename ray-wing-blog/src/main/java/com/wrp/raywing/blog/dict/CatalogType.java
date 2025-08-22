package com.wrp.raywing.blog.dict;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wrp.raywing.common.dict.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 目录分类
 * @author wrp
 * @since 2025年08月22日 14:04
 **/
@Getter
@AllArgsConstructor
public enum CatalogType implements BaseEnum {
    DIRECTORY(1, "目录"),
    BOOK(2, "书籍"),
    ;
    @EnumValue
    @JsonValue
    private final Integer code;
    private final String value;
}
