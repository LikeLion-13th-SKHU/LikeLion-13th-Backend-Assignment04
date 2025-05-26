package com.likelion.likelioncrud.movie.application;

import com.likelion.likelioncrud.common.exception.BusinessException;
import com.likelion.likelioncrud.common.error.ErrorCode;
import com.likelion.likelioncrud.movie.api.dto.request.MovieSaveRequestDto;
import com.likelion.likelioncrud.movie.api.dto.request.MovieUpdateRequestDto;
import com.likelion.likelioncrud.movie.api.dto.response.MovieInfoResponseDto;
import com.likelion.likelioncrud.movie.api.dto.response.MovieListResponseDto;
import com.likelion.likelioncrud.movie.domain.Movie;
import com.likelion.likelioncrud.movie.domain.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;

    // 영화 저장
    @Transactional
    public void movieSave(MovieSaveRequestDto dto) {
        Movie movie = Movie.builder()
                .title(dto.title())
                .genre(dto.genre())
                .runningTime(dto.runningTime())
                .build();
        movieRepository.save(movie);
    }

    // 영화 전체 조회 (기존 - 페이징 없이)
    public MovieListResponseDto movieFindAll() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieInfoResponseDto> dtoList = movies.stream()
                .map(MovieInfoResponseDto::from)
                .toList();
        return MovieListResponseDto.from(dtoList);
    }

    // 영화 전체 조회 (📌 페이징 추가)
    public MovieListResponseDto movieFindAll(Pageable pageable) {
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        List<MovieInfoResponseDto> dtoList = moviePage.getContent().stream()
                .map(MovieInfoResponseDto::from)
                .toList();
        return MovieListResponseDto.from(dtoList);
    }

    // 단일 영화 조회
    public MovieInfoResponseDto movieFindOne(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.MOVIE_NOT_FOUND_EXCEPTION,
                                ErrorCode.MOVIE_NOT_FOUND_EXCEPTION.getMessage() + movieId));
        return MovieInfoResponseDto.from(movie);
    }

    // 영화 수정
    @Transactional
    public void movieUpdate(Long movieId, MovieUpdateRequestDto dto) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.MOVIE_NOT_FOUND_EXCEPTION,
                                ErrorCode.MOVIE_NOT_FOUND_EXCEPTION.getMessage() + movieId));
        movie.update(dto);
    }

    // 영화 삭제
    @Transactional
    public void movieDelete(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.MOVIE_NOT_FOUND_EXCEPTION,
                                ErrorCode.MOVIE_NOT_FOUND_EXCEPTION.getMessage() + movieId));
        movieRepository.delete(movie);
    }
}
