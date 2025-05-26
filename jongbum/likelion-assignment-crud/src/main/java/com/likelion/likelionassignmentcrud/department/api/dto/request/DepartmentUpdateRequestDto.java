package com.likelion.likelionassignmentcrud.department.api.dto.request;

import com.likelion.likelionassignmentcrud.department.domain.Part;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DepartmentUpdateRequestDto(
        // 업데이트할 변수 입력
        @NotNull(message = "부서의 이름을 필수로 입력해야 합니다.")
        @NotBlank(message = "공백은 사용하실 수 없습니다.")
        @Size(min = 2, max = 12)
        String name,

        Part part
) {
}
