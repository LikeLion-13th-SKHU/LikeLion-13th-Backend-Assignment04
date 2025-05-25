package com.likelion.likelionassignmentcrud.delivery.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record DeliveryListResponseDto(
        List<DeliveryInfoResponseDto> deliverys
) {

    public static DeliveryListResponseDto from(List<DeliveryInfoResponseDto> deliverys){
        return DeliveryListResponseDto.builder()
                .deliverys(deliverys)
                .build();
    }
}
