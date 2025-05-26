package com.likelion.likelioncrud.movie.api.dto.response;

import com.likelion.likelioncrud.movie.domain.Movie;
import lombok.Builder;

@Builder
public record MovieInfoResponseDto(
        String title,
        String genre,
        int runningTime
) {
    public static MovieInfoResponseDto from(Movie movie) {
        return MovieInfoResponseDto.builder()
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .runningTime(movie.getRunningTime())
                .build();
    }
}
