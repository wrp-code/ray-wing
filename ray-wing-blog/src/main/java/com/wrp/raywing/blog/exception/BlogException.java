package com.wrp.raywing.blog.exception;

import com.wrp.raywing.common.exception.BaseException;

/**
 * @author wrp
 * @since 2025年08月18日 12:01
 **/
public class BlogException extends BaseException {
    public BlogException() {
    }

    public BlogException(String message) {
        super(message);
    }

    public BlogException(String message, Throwable cause) {
        super(message, cause);
    }
}
