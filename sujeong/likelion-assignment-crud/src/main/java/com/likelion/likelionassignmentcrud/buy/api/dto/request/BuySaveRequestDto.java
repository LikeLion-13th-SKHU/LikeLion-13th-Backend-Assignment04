package com.likelion.likelionassignmentcrud.buy.api.dto.request;

import jakarta.validation.constraints.*;

public record BuySaveRequestDto(
        @NotBlank(message = "제품을 필수로 입력해야 합니다.")
        String productId,

        @NotEmpty(message = "구매자를 필수로 입력해야 합니다.")
        @Size(min = 2, max = 20)
        String consumer,

        @NotNull(message = "배송일을 필수로 입력해야 합니다.")
        @Pattern(
                regexp = "^(0?[1-9]|1[0-2])월\\s(0?[1-9]|[12][0-9]|3[01])일$",
                message = "배송일 형식은 'M월 D일'이어야 합니다."
        )
        String delivery
) {
}