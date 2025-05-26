package com.kimminwoo.likelionassignmentcrud.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    // 404 NOT FOUND
    STUDENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 학생을 찾을 수 없습니다.", "STUDENT_NOT_FOUND_404"),
    ASSIGNMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 과제를 찾을 수 없습니다.", "ASSIGNMENT_NOT_FOUND_404"),

    // 400 BAD REQUEST
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "유효성 검사에 실패하였습니다.", "VALIDATION_ERROR_400"),

    // 500 INTERNAL ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.", "INTERNAL_ERROR_500");

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
