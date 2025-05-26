package com.kimminwoo.likelionassignmentcrud.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회되었습니다."),
    STUDENT_UPDATE_SUCCESS(HttpStatus.OK, "학생이 성공적으로 수정되었습니다."),
    ASSIGNMENT_UPDATE_SUCCESS(HttpStatus.OK, "과제가 성공적으로 수정되었습니다."),
    STUDENT_DELETE_SUCCESS(HttpStatus.OK, "학생이 성공적으로 삭제되었습니다."),
    ASSIGNMENT_DELETE_SUCCESS(HttpStatus.OK, "과제가 성공적으로 삭제되었습니다."),

    STUDENT_SAVE_SUCCESS(HttpStatus.CREATED, "학생이 성공적으로 저장되었습니다."),
    ASSIGNMENT_SAVE_SUCCESS(HttpStatus.CREATED, "과제가 성공적으로 저장되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
