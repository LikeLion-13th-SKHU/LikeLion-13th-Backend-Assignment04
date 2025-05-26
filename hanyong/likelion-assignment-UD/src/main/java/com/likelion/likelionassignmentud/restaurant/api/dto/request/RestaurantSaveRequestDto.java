package com.likelion.likelionassignmentud.restaurant.api.dto.request;

import com.likelion.likelionassignmentud.restaurant.domain.Part;

public record RestaurantSaveRequestDto(
        String restaurantName,
        String number,
        Part part
) {
}
