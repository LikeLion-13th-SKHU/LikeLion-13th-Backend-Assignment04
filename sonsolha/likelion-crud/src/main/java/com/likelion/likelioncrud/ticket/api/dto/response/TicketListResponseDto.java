package com.likelion.likelioncrud.ticket.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record TicketListResponseDto(
        List<TicketInfoResponseDto> tickets
) {
    public static TicketListResponseDto from(List<TicketInfoResponseDto> tickets) {
        return TicketListResponseDto.builder()
                .tickets(tickets)
                .build();
    }
}
