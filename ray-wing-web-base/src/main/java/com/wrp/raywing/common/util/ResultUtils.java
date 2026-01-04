package com.wrp.raywing.common.util;

import com.wrp.raywing.common.dict.ResultCode;
import com.wrp.raywing.common.domain.Result;

/**
 * @author wrp
 * @since 2026年01月04日 10:39
 **/
public class ResultUtils {

    /**
     * 返回成功，无数据
     */
    public static Result<Void> success() {
        return success(null);
    }

    /**
     * 返回成功，带数据
     */
    public static <R> Result<R> success(R data) {
        return of(ResultCode.SUCCESS, data);
    }

    /**
     * 返回失败，没有数据
     */
    public static Result<Void> error() {
        return of(ResultCode.ERROR, null);
    }

    /**
     * 返回失败，没有数据
     */
    public static Result<Void> error(String message) {
        return new Result<>(ResultCode.ERROR.getCode(), message, null);
    }

    private static <R> Result<R> of(ResultCode code, R data) {
        return new Result<>(code.getCode(), code.getValue(), data);
    }

}
