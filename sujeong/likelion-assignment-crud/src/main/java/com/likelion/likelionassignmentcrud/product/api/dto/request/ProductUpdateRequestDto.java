package com.likelion.likelionassignmentcrud.product.api.dto.request;

import com.likelion.likelionassignmentcrud.product.domain.Part;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record ProductUpdateRequestDto(
        @NotBlank(message = "이름은 필수로 입력해야 합니다.")
        String name,

        @PositiveOrZero(message = "가격은 0원 이상이어야 합니다.")
        int price,

        @Size(min=1, max=8)
        Part part

) {
}