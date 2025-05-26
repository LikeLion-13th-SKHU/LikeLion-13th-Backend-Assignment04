package com.likelion.likelionassignmentcrud.buy.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record BuyListResponseDto(
        List<BuyInfoResponseDto> buys
) {
    public static BuyListResponseDto from(List<BuyInfoResponseDto> buys) {
        return BuyListResponseDto.builder()
                .buys(buys)
                .build();
    }
}