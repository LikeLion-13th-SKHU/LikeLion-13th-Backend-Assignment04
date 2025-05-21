package com.likelion.springcrud.common.template;

import com.likelion.springcrud.common.error.ErrorCode;
import com.likelion.springcrud.common.error.SuccessCode;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ApiResponse<T> {

    private final int code;
    private final String message;
    private T data;
    private final LocalDateTime timestamp;


    // 데이터가 없는 성공 응답
    public static ApiResponse success(SuccessCode successCode) {
        return new ApiResponse<>(successCode.getHttpStatusCode(), successCode.getMessage(), LocalDateTime.now());
    }

    // 데이터를 포함한 성공 응답 (다형성)
    public static <T> ApiResponse<T> success(SuccessCode successCode, T data) {
        return new ApiResponse<>(successCode.getHttpStatusCode(), successCode.getMessage(), data, LocalDateTime.now());
    }

    // 에러 응답
    public static ApiResponse errorResponse(ErrorCode errorCode, String customMessage) {
        return new ApiResponse<>(errorCode.getHttpStatusCode(), customMessage, LocalDateTime.now());
    }
}
