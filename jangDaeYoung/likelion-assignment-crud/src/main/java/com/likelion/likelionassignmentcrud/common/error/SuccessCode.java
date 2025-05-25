package com.likelion.likelionassignmentcrud.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),

    //고객 정보 저장, 업데이트, 삭제 성공
    CLIENT_SAVE_SUCCESS(HttpStatus.CREATED, "고객 정보가 성공적으로 생성되었습니다."),
    CLIENT_UPDATE_SUCCESS(HttpStatus.OK, "고객 정보가 성공적으로 수정되었습니다."),
    CLIENT_DELETE_SUCCESS(HttpStatus.OK, "사용자가 성공적으로 삭제되었습니다."),

    //주문 정보 저장, 업데이트, 삭제
    DELIVERY_SAVE_SUCCESS(HttpStatus.CREATED, "글이 성공적으로 생성되었습니다."),
    DELIVERY_UPDATE_SUCCESS(HttpStatus.OK, "주문 정보가 성공적으로 수정되었습니다."),
    DELIVERY_DELETE_SUCCESS(HttpStatus.OK, "주문이 성공적으로 삭제되었습니다.");


    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}





