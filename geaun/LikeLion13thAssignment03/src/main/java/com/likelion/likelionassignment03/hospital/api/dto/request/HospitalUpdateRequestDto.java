package com.likelion.likelionassignment03.hospital.api.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record HospitalUpdateRequestDto(
        @NotBlank(message = "병원 이름은 필수입니다.")
        @Size(min = 2, max = 30, message = "병원 이름은 2자 이상 30자 이하여야 합니다.")
        String name,
        @NotNull(message = "병원 위치 정보는 필수입니다.")
        String location
) {
}

      
