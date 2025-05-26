package com.likelion.likelioncrud.lectures.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record LectureListResponseDto(
        List<LectureInfoResponseDto> lectures
) {
public static LectureListResponseDto from(List<LectureInfoResponseDto> lectures) {
    return LectureListResponseDto.builder()
            .lectures(lectures)
            .build();
}
}