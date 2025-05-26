package com.likelion.likelionassignment03.patient.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PatientSaveRequestDto(
        @NotNull(message = "나이를 필수로 입력해야 합니다.")
        @Positive(message = "나이는 양수여야 합니다")
        int age,
        @NotBlank(message = "원인을 필수로 입력해야 합니다.")
        @Size(min = 3, max = 20)
        String causes,
        @NotNull(message = "작성자를 필수로 입력해야 합니다.")
        @Positive(message = "병원 ID는 양수여야 합니다")
        Long hospitalId
) {

}