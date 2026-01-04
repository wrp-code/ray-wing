package com.wrp.raywing.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回结果
 * @author wrp
 * @since 2025年07月04日 20:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result <T> {
    private int code;
    private String message;
    private T data;
}
