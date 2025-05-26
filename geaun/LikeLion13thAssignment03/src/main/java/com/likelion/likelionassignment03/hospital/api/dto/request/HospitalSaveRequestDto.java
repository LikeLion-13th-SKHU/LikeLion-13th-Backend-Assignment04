package com.likelion.likelionassignment03.hospital.api.dto.request;

import com.likelion.likelionassignment03.hospital.domain.Location;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record HospitalSaveRequestDto(

        @NotBlank(message = "병원 이름은 필수입니다.")
        @Size(min = 2, max = 30, message = "병원 이름은 2자 이상 30자 이하여야 합니다.")
        String name,

        @NotNull(message = "설립 연도는 필수 입니다.")
        @Positive(message = "설립 연도는 양수여야 합니다.")
        int establishedYears,

        @NotNull(message = "병원 위치 정보는 필수입니다.")
        Location location

) {
}

