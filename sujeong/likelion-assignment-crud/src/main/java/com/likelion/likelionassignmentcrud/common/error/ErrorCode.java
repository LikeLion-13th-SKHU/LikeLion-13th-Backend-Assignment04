package com.likelion.likelionassignmentcrud.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    /**
     * 404 NOT FOUND
     */
    PRODUCT_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 제품이 없습니다. productId = ", "NOT_FOUND_404"),
    BUY_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 구매 목록이 없습니다. buyId = ", "NOT_FOUND_404"),

    /**
     * 500 INTERNAL SERVER ERROR
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다", "INTERNAL_SERVER_ERROR_500"),

    /**
     * 400 BAD REQUEST
     */
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "유효성 검사에 실패하였습니다.", "BAD_REQUEST_400");

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}