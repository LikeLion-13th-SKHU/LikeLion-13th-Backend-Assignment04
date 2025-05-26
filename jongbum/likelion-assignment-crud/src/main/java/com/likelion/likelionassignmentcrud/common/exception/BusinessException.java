package com.likelion.likelionassignmentcrud.common.exception;

import com.likelion.likelionassignmentcrud.common.error.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
  private final ErrorCode errorCode;
  private final String customMessage;

  // 생성자
  public BusinessException(ErrorCode errorCode, String customMessage) {
    super(customMessage);
    this.errorCode = errorCode;
    this.customMessage = customMessage;
  }
}
