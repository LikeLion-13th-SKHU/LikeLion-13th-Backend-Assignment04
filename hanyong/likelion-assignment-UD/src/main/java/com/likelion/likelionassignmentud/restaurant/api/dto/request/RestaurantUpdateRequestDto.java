package com.likelion.likelionassignmentud.restaurant.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RestaurantUpdateRequestDto(
        @NotNull(message = "식당 이름을 입력해야 합니다.")
        @Size(min = 2, max = 10)
        String restaurantName,
        @NotBlank(message = "번호를 입력해야 합니다.")
        String number
) {
}
