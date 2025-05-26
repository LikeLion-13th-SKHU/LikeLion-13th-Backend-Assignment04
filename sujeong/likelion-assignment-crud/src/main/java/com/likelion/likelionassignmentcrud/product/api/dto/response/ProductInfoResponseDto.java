package com.likelion.likelionassignmentcrud.product.api.dto.response;

import com.likelion.likelionassignmentcrud.product.domain.Product;
import com.likelion.likelionassignmentcrud.product.domain.Part;
import lombok.Builder;

@Builder
public record ProductInfoResponseDto(
        String name,
        int price,
        Part part
) {
    public static ProductInfoResponseDto from(Product product) {
        return ProductInfoResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .part(product.getPart())
                .build();
    }
}