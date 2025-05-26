package com.likelion.likelioncrud.movie.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record MovieListResponseDto(
        List<MovieInfoResponseDto> movies
) {
    public static MovieListResponseDto from(List<MovieInfoResponseDto> movies) {
        return MovieListResponseDto.builder()
                .movies(movies)
                .build();
    }
}
