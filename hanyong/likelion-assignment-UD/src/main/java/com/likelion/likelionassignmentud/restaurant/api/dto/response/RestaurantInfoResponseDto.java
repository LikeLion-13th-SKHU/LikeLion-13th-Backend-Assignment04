package com.likelion.likelionassignmentud.restaurant.api.dto.response;

import com.likelion.likelionassignmentud.restaurant.domain.Part;
import com.likelion.likelionassignmentud.restaurant.domain.Restaurant;
import lombok.Builder;

@Builder
public record RestaurantInfoResponseDto(
        String name,
        String number,
        Part part
) {
    public static RestaurantInfoResponseDto from(Restaurant restaurant) {
        return RestaurantInfoResponseDto.builder()
                .name(restaurant.getRestaurantName())
                .number(restaurant.getNumber())
                .part(restaurant.getPart())
                .build();
    }
}
