package com.likelion.likelioncrud.movie.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;

public record MovieSaveRequestDto(

        @NotBlank(message = "영화 제목은 비워둘 수 없습니다.")
        @Size(min = 1, max = 50, message = "영화 제목은 1자 이상 50자 이하로 입력해주세요.")
        String title,

        @NotBlank(message = "장르는 비워둘 수 없습니다.")
        String genre,

        @Positive(message = "상영 시간은 1분 이상이어야 합니다.")
        int runningTime

) {
}
