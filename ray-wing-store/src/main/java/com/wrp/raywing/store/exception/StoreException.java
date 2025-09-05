package com.wrp.raywing.store.exception;

import com.wrp.raywing.common.exception.BaseException;

/**
 * @author wrp
 * @since 2025年08月25日 12:37
 **/
public class StoreException extends BaseException {

    public StoreException(String message) {
        super(message);
    }

    public StoreException() {
    }

    public StoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
