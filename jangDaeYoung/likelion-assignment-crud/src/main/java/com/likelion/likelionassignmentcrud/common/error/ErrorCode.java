package com.likelion.likelionassignmentcrud.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    CLIENT_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 고객이 없습니다. clientId = ", "NOT_FOUND_404"),
    DELIVERY_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 배달이 없습니다. deliveryId = ", "NOT_FOUND_404"),


    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 에러가 발생했습니다", "INTERNAL_SERVER_ERROR_500"),

    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "유효성 검사에 실패하였습니다.", "BAD_REQUEST_400");

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
