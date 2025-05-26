package com.likelion.likelionassignmentcrud.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter // getter 메소드 자동 생성 lombok 어노테이션
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 필드를 파라미터로 받는 생성자 자동 생성 어노테이션
public enum SuccessCode {

    /**
     * 200 OK (성공)
     */
    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),
    PRODUCT_UPDATE_SUCCESS(HttpStatus.OK, "제품이 성공적으로 수정되었습니다."),
    BUY_UPDATE_SUCCESS(HttpStatus.OK, "구매 목록이 성공적으로 수정되었습니다."),
    PRODUCT_DELETE_SUCCESS(HttpStatus.OK, "제품이 성공적으로 삭제되었습니다."),
    BUY_DELETE_SUCCESS(HttpStatus.OK, "구매 목록이 성공적으로 삭제되었습니다."),


    /**
     * 201 CREATED (생성 성공)
     */
    PRODUCT_SAVE_SUCCESS(HttpStatus.CREATED, "제품이 성공적으로 생성되었습니다."),
    BUY_CREATE_SUCCESS(HttpStatus.CREATED, "구매 목록이 성공적으로 생성되었습니다.");

    private final HttpStatus httpStatus;   // HTTP 상태 코드 ex) 200, 201
    private final String message;          // 응답 메세지

    public int getHttpStatusCode() {       // HTTP 상태 코드에서 404와 같은 숫자 값만 반환해 주기 위한 메소드
        return httpStatus.value();
    }
}
