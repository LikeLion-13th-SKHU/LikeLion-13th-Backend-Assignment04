package com.likelion.springcrud.member.api.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record MemberSaveRequestDto(
        @NotEmpty(message = "null 값이거나 공백이어서는 안 됩니다.")
        String name,
        @PositiveOrZero(message = "나이는 0 이상의 값을 입력해주세요.")
        @NotNull(message = "null 값이 들어오면 안 됩니다.")
        int age
) {
}
