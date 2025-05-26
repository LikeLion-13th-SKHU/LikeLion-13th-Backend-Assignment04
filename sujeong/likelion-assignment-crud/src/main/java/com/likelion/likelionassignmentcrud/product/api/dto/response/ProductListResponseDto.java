package com.likelion.likelionassignmentcrud.product.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductListResponseDto(
        List<ProductInfoResponseDto> products
) {
    public static ProductListResponseDto from(List<ProductInfoResponseDto> products) {
        return ProductListResponseDto.builder()
                .products(products)
                .build();
    }
}