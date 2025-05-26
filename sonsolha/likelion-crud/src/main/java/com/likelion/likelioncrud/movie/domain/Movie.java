package com.likelion.likelioncrud.movie.domain;

import com.likelion.likelioncrud.movie.api.dto.request.MovieUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    private String title;

    private String genre;

    private int runningTime;

    @Builder
    private Movie(String title, String genre, int runningTime) {
        this.title = title;
        this.genre = genre;
        this.runningTime = runningTime;
    }

    public void update(MovieUpdateRequestDto dto) {
        this.title = dto.title();
        this.genre = dto.genre();
        this.runningTime = dto.runningTime();
    }
}
