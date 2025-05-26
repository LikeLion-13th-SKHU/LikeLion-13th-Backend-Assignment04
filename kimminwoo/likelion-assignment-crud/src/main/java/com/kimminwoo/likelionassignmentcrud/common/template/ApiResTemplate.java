package com.kimminwoo.likelionassignmentcrud.common.template;

import com.kimminwoo.likelionassignmentcrud.common.error.ErrorCode;
import com.kimminwoo.likelionassignmentcrud.common.error.SuccessCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResTemplate<T> {
    private final int code;
    private final String message;
    private final T data;

    public static <T> ApiResTemplate<T> successWithNoContent(SuccessCode successCode) {
        return new ApiResTemplate<>(successCode.getHttpStatusCode(), successCode.getMessage(), null);
    }

    public static <T> ApiResTemplate<T> successResponse(SuccessCode successCode, T data) {
        return new ApiResTemplate<>(successCode.getHttpStatusCode(), successCode.getMessage(), data);
    }

    public static <T> ApiResTemplate<T> errorResponse(ErrorCode errorCode, String customMessage) {
        return new ApiResTemplate<>(errorCode.getHttpStatusCode(), customMessage, null);
    }
}
