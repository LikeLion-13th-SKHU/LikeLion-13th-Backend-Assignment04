package com.likelion.likelionassignmentud.restaurant.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record RestaurantListResponseDto(
        List<RestaurantInfoResponseDto> restaurants
) {
    public static RestaurantListResponseDto from(List<RestaurantInfoResponseDto> restaurant) {
        return RestaurantListResponseDto.builder()
                .restaurants(restaurant)
                .build();
    }
}
