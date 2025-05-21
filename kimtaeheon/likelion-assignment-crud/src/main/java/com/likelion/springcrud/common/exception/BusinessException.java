package com.likelion.springcrud.common.exception;

import com.likelion.springcrud.common.error.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String customMessage;

    public BusinessException(ErrorCode errorCode, String customMessage) {
        super(customMessage); // RuntimeException 생성자로 전달
        this.errorCode = errorCode;
        this.customMessage = customMessage;
    }
}
