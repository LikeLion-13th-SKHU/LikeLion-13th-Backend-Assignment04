package com.likelion.likelionassignmentud.common.exception;

import com.likelion.likelionassignmentud.common.error.ErrorCode;
import lombok.Getter;

@Getter //getter 메소드 자동 생성 lambok 어노테이션
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String customMessage;

    //생성자
    public BusinessException(ErrorCode errorCode, String customMessage) {
        super(customMessage);
        this.errorCode = errorCode;
        this.customMessage = customMessage;
    }
}
