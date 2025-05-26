package com.likelion.likelionassignmentcrud.employee.api.dto.request;

import com.likelion.likelionassignmentcrud.department.domain.Part;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmployeeSaveRequestDto(
        @NotNull(message = "부서는 필수로 입력해야합니다.")
        Long departmentId,

        @NotNull(message = "부서는 필수로 입력해야합니다.")
        @NotBlank(message = "공백을 사용하실 수 없습니다.")
        @Size(min = 2, max = 10)    // 최소 2글자, 최대 10글자
        String name,

        int age,

        @NotNull(message = "직급은 필수로 입력해야 합니다.")
        @NotBlank(message = "공백을 사용하실 수 없습니다.")
        String responsibilities,

        Part part
) {
}
