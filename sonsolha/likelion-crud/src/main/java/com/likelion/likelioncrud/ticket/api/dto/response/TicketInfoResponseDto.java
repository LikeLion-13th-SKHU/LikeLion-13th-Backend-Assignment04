package com.likelion.likelioncrud.ticket.api.dto.response;

import com.likelion.likelioncrud.ticket.domain.Ticket;
import lombok.Builder;

@Builder
public record TicketInfoResponseDto(
        String movieTitle,
        String customerName,
        String seatNumber
) {
    public static TicketInfoResponseDto from(Ticket ticket) {
        return TicketInfoResponseDto.builder()
                .movieTitle(ticket.getMovie().getTitle())
                .customerName(ticket.getCustomerName())
                .seatNumber(ticket.getSeatNumber())
                .build();
    }
}
