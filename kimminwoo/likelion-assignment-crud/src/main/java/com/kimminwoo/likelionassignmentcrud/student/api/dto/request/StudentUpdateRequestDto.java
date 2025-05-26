package com.kimminwoo.likelionassignmentcrud.student.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StudentUpdateRequestDto(

        @NotBlank(message = "이름은 필수입니다.")
        @Size(min = 2, max = 20, message = "이름은 2~20자 사이여야 합니다.")
        String name,

        @NotBlank(message = "전화번호는 필수입니다.")
        @Size(min = 10, max = 11, message = "전화번호는 10~11자여야 합니다.")
        String phoneNumber

) {}
