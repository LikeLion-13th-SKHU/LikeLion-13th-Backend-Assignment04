package com.likelion.likelionassignment03.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    HOSPITAL_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 병원이 없습니다. hospitalId = ", "NOT_FOUND_404"),
    PATIENT_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 환자가 없습니다. patientId = ", "NOT_FOUND_404"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 에러가 발생했습니다", "INTERNAL_SERVER_ERROR_500"),

    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "유효성 검사에 실패하였습니다.","BAD_REQUEST_400");

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}