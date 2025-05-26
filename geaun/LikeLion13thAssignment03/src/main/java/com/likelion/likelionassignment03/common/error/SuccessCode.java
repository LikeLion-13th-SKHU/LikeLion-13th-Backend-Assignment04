package com.likelion.likelionassignment03.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {


    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),
    HOSPITAL_UPDATE_SUCCESS(HttpStatus.OK, "병원이 성공적으로 수정되었습니다."),
    PATIENT_UPDATE_SUCCESS(HttpStatus.OK, "환자가 성공적으로 수정되었습니다."),
    HOSPITAL_DELETE_SUCCESS(HttpStatus.OK, "병원이 성공적으로 삭제되었습니다."),
    PATIENT_DELETE_SUCCESS(HttpStatus.OK, "환자가 성공적으로 삭제되었습니다."),

    HOSPITAL_SAVE_SUCCESS(HttpStatus.CREATED, "병원이 성공적으로 생성되었습니다."),
    PATIENT_SAVE_SUCCESS(HttpStatus.CREATED, "사용자가 성공적으로 생성되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}

