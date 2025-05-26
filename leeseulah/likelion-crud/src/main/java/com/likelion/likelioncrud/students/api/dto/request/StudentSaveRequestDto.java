package com.likelion.likelioncrud.students.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record StudentSaveRequestDto(
        @NotNull(message = "이름을 필수로 입력해야 합니다.")
        String name,
        @Max(value = 4, message = "학년은 최대 4학년까지 입력 가능합니다.")
        int grade,
        @Positive(message = "강의 ID는 양수여야 합니다.")
        Long lectureId
) {
}