package com.likelion.likelionassignmentcrud.delivery.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DeliveryUpdateRequestDto(

        @NotBlank(message = "아이템 이름을 필수로 입력해야 합니다.")
        @Size(min = 2, max = 20)
        String itemName,

        @NotBlank(message = "배달 상태를 필수로 입력해야 합니다.")
        String deliveryStatus
) {
}

