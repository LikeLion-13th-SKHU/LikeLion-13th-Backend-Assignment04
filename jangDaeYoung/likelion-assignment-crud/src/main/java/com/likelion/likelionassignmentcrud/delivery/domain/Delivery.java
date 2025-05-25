package com.likelion.likelionassignmentcrud.delivery.domain;

import com.likelion.likelionassignmentcrud.client.domain.Client;
import com.likelion.likelionassignmentcrud.delivery.api.dto.request.DeliveryUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long deliveryId;

    private String itemName;

    private String deliveryStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Builder
    private Delivery(String itemName, String deliveryStatus, Client client){
        this.itemName = itemName;
        this.deliveryStatus = deliveryStatus;
        this.client = client;

    }

    public void update(DeliveryUpdateRequestDto deliveryUpdateRequestDto){
        this.itemName = deliveryUpdateRequestDto.itemName();
        this.deliveryStatus = deliveryUpdateRequestDto.deliveryStatus();
    }






}
