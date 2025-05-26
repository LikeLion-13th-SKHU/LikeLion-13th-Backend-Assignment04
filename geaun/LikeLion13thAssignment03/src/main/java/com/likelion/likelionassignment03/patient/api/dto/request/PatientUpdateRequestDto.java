package com.likelion.likelionassignment03.patient.api.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record PatientUpdateRequestDto(
        @NotBlank(message = "원인을 필수로 입력해야 합니다.")
        @Positive(message = "나이는 양수여야 합니다")
        String causes,
        @NotNull(message = "나이를 필수로 입력해야 합니다.")
        @NotNull(message = "수정값이 공백이어서는 안 됩니다.")
        int age
) {
}


