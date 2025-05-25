package com.likelion.likelionassignmentcrud.delivery.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record DeliverySaveRequestDto (
        @Positive //양수만 입력
        Long clientId,

        @NotBlank(message = "id를 필수로 입력해야 합니다.")
        String itemName,

        @NotBlank(message = "배달 상태를 필수로 입력해야 합니다.")
        String deliveryStatus
){
}
