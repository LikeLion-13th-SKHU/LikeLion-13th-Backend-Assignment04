package com.likelion.likelionassignmentcrud.client.api.dto.request;

import com.likelion.likelionassignmentcrud.client.domain.Payment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public record ClientSaveRequestDto (
        @NotBlank(message = "이름은 필수로 입력해야 합니다.")
        @Size(min = 2, max = 20)
        String name,

        @Positive(message = "양수만 허용합니다.") //양수만 허용
        int age,


        //enum타입 예외처리 복잡해 보여서 일단 제외......
        Payment payment
){

}
