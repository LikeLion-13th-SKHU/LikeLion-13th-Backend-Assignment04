package com.likelion.likelionassignmentcrud.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    // 200 OK
    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),
    DEPARTMENT_UPDATE_SUCCESS(HttpStatus.OK, "부서가 성공적으로 수정됐습니다."),
    EMPLOYEE_UPDATE_SUCCESS(HttpStatus.OK, "직원이 성공적으로 수정됐습니다."),
    DEPARTMENT_DELETE_SUCCESS(HttpStatus.OK, "부서가 성공적으로 삭제됐습니다."),
    EMPLOYEE_DELETE_SUCCESS(HttpStatus.OK, "직원이 성공적으로 삭제됐습니다."),

    // 201 CREATE
    DEPARTMENT_SAVE_SUCCESS(HttpStatus.CREATED, "부서가 성공적으로 생성됐습니다."),
    EMPLOYEE_SAVE_SUCCESS(HttpStatus.CREATED, "직원이 성공적으로 생성됐습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() { return httpStatus.value(); }
}
