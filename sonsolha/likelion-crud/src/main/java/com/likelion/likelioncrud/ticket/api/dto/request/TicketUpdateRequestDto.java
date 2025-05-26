package com.likelion.likelioncrud.ticket.api.dto.request;
import jakarta.validation.constraints.Size;


import jakarta.validation.constraints.NotBlank;

public record TicketUpdateRequestDto(

        @NotBlank(message = "고객 이름은 비워둘 수 없습니다.")
        @Size(min = 2, max = 20, message = "이름은 2자 이상 20자 이하로 입력해주세요.")
        String customerName,

        @NotBlank(message = "좌석 번호는 비워둘 수 없습니다.")
        String seatNumber

) {
}
