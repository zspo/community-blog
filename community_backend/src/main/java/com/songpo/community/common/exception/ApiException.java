package com.songpo.community.common.exception;

import com.songpo.community.common.api.IErrorCode;


public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    protected ApiException() {

    }

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }

    public static ApiException withErrorCode(IErrorCode errorCode) {
        ApiException apiException = new ApiException();
        apiException.errorCode = errorCode;
        return apiException;
    }
}
