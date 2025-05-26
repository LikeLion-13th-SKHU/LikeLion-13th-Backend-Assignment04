package com.likelion.likelioncrud.lectures.api.dto.response;

import com.likelion.likelioncrud.lectures.domain.Lecture;
import lombok.Builder;

@Builder
public record LectureInfoResponseDto(
        String name,
        String title,
        String content
) {
    public static LectureInfoResponseDto from(Lecture lecture) {
        return LectureInfoResponseDto.builder()
                .name(lecture.getName())
                .title(lecture.getTitle())
                .content(lecture.getContent())
                .build();
    }
}