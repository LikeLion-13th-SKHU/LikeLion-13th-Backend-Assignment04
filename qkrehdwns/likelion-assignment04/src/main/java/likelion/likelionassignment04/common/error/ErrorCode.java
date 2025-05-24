package likelion.likelionassignment04.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    COUNTRY_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 국가가 없습니다. countryId = ", "NOT_FOUND_404"),
    CITY_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 도시가 없습니다. cityId = ", "NOT_FOUND_404"),

    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.", "BAD_REQUEST_400"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다.", "INTERNAL_SERVER_ERROR_500");

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }


}
