package com.kimminwoo.likelionassignmentcrud.assignment.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AssignmentUpdateRequestDto(

        @NotBlank(message = "제목은 비워둘 수 없습니다.")
        @Size(min = 2, max = 100, message = "제목은 2자 이상 100자 이하이어야 합니다.")
        String title,

        @NotBlank(message = "내용은 비워둘 수 없습니다.")
        @Size(min = 5, max = 1000, message = "내용은 5자 이상 1000자 이하이어야 합니다.")
        String content

) {}
