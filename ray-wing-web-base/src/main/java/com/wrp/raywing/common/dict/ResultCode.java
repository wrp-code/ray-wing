package com.wrp.raywing.common.dict;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wrp
 * @since 2025年07月04日 20:38
 */
@AllArgsConstructor
@Getter
public enum ResultCode implements BaseEnum {
    SUCCESS(0, "成功"),
    ERROR(1, "失败")
    ;

    @EnumValue
    @JsonValue
    private final int code;
    private final String value;
}
