package com.likelion.likelioncrud.lectures.api.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LectureSaveRequestDto(
        @NotNull(message = "이름을 필수로 입력해야 합니다.")
        String name,
        @Size(min = 3, max = 20)
        String title,
        @NotEmpty(message = "내용을 입력해야 합니다.")
        String content
) {
}