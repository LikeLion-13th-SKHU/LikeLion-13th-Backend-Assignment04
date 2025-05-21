package com.likelion.springcrud.common.exception;

import com.likelion.springcrud.common.error.ErrorCode;
import com.likelion.springcrud.common.template.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class CustomExceptionHandler {

    // 500 Internal Server Error
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // HTTP 500 상태 코드 반환
    @ExceptionHandler(Exception.class)
    public ApiResponse handleServerException(final Exception e) {
        log.error("Internal Server Error: {}", e.getMessage(), e);
        return ApiResponse.errorResponse(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    // BusinessException
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse> handleCustomException(BusinessException e) {
        log.error("CustomException: {}", e.getMessage(), e);
        ApiResponse<?> body = ApiResponse.errorResponse(e.getErrorCode(), e.getCustomMessage());

        return ResponseEntity
                .status(e.getErrorCode().getHttpStatusCode())
                .body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        // 모든 필드 에러 메시지를 수집
        List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        // 에러 메시지를 한 문장으로 합치거나, 원하면 리스트를 그대로 리턴할 수도 있음
        String combinedMessage = String.join(", ", errorMessages);

        return ResponseEntity.badRequest().body(
                ApiResponse.errorResponse(ErrorCode.VALIDATION_EXCEPTION, combinedMessage)
        );
    }
}
