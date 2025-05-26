package com.likelion.likelionassignmentud.menu.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record MenuSaveRequestDto(
        @NotNull(message = "식당을 필수로 입력해야 합니다.")
        Long menuId,
        @NotBlank(message = "메뉴를 필수로 입력해야 합니다.")
        String menuName,
        @PositiveOrZero(message = "올바른 가격을 입력하세요.")
        int price
) {
}
