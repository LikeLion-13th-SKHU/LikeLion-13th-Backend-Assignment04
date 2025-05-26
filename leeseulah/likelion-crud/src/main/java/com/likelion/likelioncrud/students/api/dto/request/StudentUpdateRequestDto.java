package com.likelion.likelioncrud.students.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record StudentUpdateRequestDto(
        @NotNull(message = "이름을 필수로 입력해야 합니다.")
        String name,
        @Min(value = 1, message = "학년은 최소 1학년부터 입력 가능합니다.")
        @Max(value = 4, message = "학년은 최대 4학년까지 입력 가능합니다.")
        int grade
) {
}