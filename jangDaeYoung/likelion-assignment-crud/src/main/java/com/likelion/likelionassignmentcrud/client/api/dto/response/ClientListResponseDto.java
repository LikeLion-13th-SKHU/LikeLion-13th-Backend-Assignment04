package com.likelion.likelionassignmentcrud.client.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ClientListResponseDto(
        List<ClientInfoResponseDto> clients
) {

    public static ClientListResponseDto from(List<ClientInfoResponseDto> clients){
     return ClientListResponseDto.builder()
             .clients(clients)
             .build();
    }
}
