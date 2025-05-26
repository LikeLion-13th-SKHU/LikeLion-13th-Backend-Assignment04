package com.likelion.likelionassignmentcrud.buy.api.dto.response;

import com.likelion.likelionassignmentcrud.buy.domain.Buy;
import lombok.Builder;

@Builder
public record BuyInfoResponseDto(
        String consumer,
        String delivery,
        String product
) {
    public static BuyInfoResponseDto from(Buy buy) {
        return BuyInfoResponseDto.builder()
                .consumer(buy.getConsumer())
                .delivery(buy.getDelivery())
                .product(buy.getProduct().getName())
                .build();
    }
}