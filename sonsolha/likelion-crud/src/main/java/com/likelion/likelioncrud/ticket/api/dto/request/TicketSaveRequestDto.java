package com.likelion.likelioncrud.ticket.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TicketSaveRequestDto(

        @NotNull(message = "영화 ID는 필수입니다.")
        Long movieId,

        @NotBlank(message = "고객 이름은 필수입니다.")
        @Size(min = 2, max = 20, message = "이름은 2~20자 사이로 입력해주세요.")
        String customerName,

        @NotBlank(message = "좌석 번호는 필수입니다.")
        String seatNumber

) {
}
