package com.likelion.likelioncrud.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    // 200 OK
    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),
    MOVIE_UPDATE_SUCCESS(HttpStatus.OK, "영화가 성공적으로 수정되었습니다."),
    TICKET_UPDATE_SUCCESS(HttpStatus.OK, "예매 내역이 성공적으로 수정되었습니다."),
    MOVIE_DELETE_SUCCESS(HttpStatus.OK, "영화가 성공적으로 삭제되었습니다."),
    TICKET_DELETE_SUCCESS(HttpStatus.OK, "예매 내역이 성공적으로 삭제되었습니다."),

    // 201 CREATED
    MOVIE_SAVE_SUCCESS(HttpStatus.CREATED, "영화가 성공적으로 등록되었습니다."),
    TICKET_SAVE_SUCCESS(HttpStatus.CREATED, "예매가 성공적으로 완료되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
