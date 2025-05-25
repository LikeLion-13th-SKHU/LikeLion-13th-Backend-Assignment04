package com.likelion.likelionassignmentcrud.client.api.dto.response;

import com.likelion.likelionassignmentcrud.client.domain.Client;
import com.likelion.likelionassignmentcrud.client.domain.Payment;
import lombok.Builder;

@Builder
public record ClientInfoResponseDto (
        String name,
        int age,
        Payment payment
){
    public static ClientInfoResponseDto from(Client client){
        return ClientInfoResponseDto.builder()
                .name(client.getName())
                .age(client.getAge())
                .payment(client.getPayment())
                .build();
    }


}
