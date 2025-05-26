package com.likelion.likelionassignmentcrud.product.domain;

import com.likelion.likelionassignmentcrud.buy.domain.Buy;
import com.likelion.likelionassignmentcrud.product.api.dto.request.ProductUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    private String name;

    private int price;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Part part;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Buy> buys = new ArrayList<>();

    @Builder
    private Product(String name, int price, Part part) {
        this.name = name;
        this.price = price;
        this.part = part;
    }
    public void update(ProductUpdateRequestDto productUpdateRequestDto) {
        this.name = productUpdateRequestDto.name();
        this.price = productUpdateRequestDto.price();
    }
}