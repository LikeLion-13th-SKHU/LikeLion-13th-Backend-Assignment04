package com.likelion.likelionassignmentcrud.buy.domain;

import com.likelion.likelionassignmentcrud.buy.api.dto.request.BuyUpdateRequestDto;
import com.likelion.likelionassignmentcrud.product.domain.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Buy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buy_id")
    private Long buyId;

    private String consumer;

    private String delivery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    private Buy(String consumer, String delivery, Product product) {
        this.consumer = consumer;
        this.delivery = delivery;
        this.product = product;
    }

    public void update(BuyUpdateRequestDto buyUpdateRequestDto) {
        this.consumer = buyUpdateRequestDto.consumer();
        this.delivery = buyUpdateRequestDto.delivery();

    }
}