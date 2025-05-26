package com.likelion.likelionassignmentud.menu.api.dto.response;

import com.likelion.likelionassignmentud.menu.domain.Menu;
import lombok.Builder;

@Builder
public record MenuInfoResponseDto(
        String menuName,
        int price,
        String name
) {
    public static MenuInfoResponseDto from(Menu menu) {
        return MenuInfoResponseDto.builder()
                .menuName(menu.getMenuName())
                .price(menu.getPrice())
                .name(menu.getRestaurant().getRestaurantName())
                .build();
    }
}
