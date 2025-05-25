package com.likelion.likelionassignmentcrud.common.template;

import com.likelion.likelionassignmentcrud.common.error.ErrorCode;
import com.likelion.likelionassignmentcrud.common.error.SuccessCode;
import lombok.*;

@Getter // getter 메소드 자동 생성 lombok 어노테이션
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder // 빌더 패턴
public class ApiResTemplate<T> {

    private final int code;
    private final String message;
    private T data;

    // 데이터 없는 성공 응답
    public static ApiResTemplate successWithNoContent(SuccessCode successCode) {
        return new ApiResTemplate<>(successCode.getHttpStatusCode(), successCode.getMessage());
    }

    // 데이터 포함한 성공 응답
    public static <T> ApiResTemplate<T> successResponse(SuccessCode successCode, T data) {
        return new ApiResTemplate<>(successCode.getHttpStatusCode(), successCode.getMessage(), data);
    }

    // 에러 응답 (커스텀 메시지 포함)
    public static ApiResTemplate errorResponse(ErrorCode errorCode, String customMessage) {
        return new ApiResTemplate<>(errorCode.getHttpStatusCode(), customMessage);
    }

}