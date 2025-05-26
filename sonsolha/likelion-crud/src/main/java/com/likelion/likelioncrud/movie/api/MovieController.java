package com.likelion.likelioncrud.movie.api;

import com.likelion.likelioncrud.common.error.SuccessCode;
import com.likelion.likelioncrud.common.template.ApiResTemplate;
import com.likelion.likelioncrud.movie.api.dto.request.MovieSaveRequestDto;
import com.likelion.likelioncrud.movie.api.dto.request.MovieUpdateRequestDto;
import com.likelion.likelioncrud.movie.api.dto.response.MovieInfoResponseDto;
import com.likelion.likelioncrud.movie.api.dto.response.MovieListResponseDto;
import com.likelion.likelioncrud.movie.application.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    // 페이징 전체 조회
    @GetMapping("/all")
    public ApiResTemplate<MovieListResponseDto> movieFindAll(Pageable pageable) {
        MovieListResponseDto response = movieService.movieFindAll(pageable);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, response);
    }

    // 영화 저장
    @PostMapping("/save")
    public ApiResTemplate<String> movieSave(@RequestBody @Valid MovieSaveRequestDto movieSaveRequestDto) {
        movieService.movieSave(movieSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.MOVIE_SAVE_SUCCESS);
    }

    // 영화 id 단건 조회
    @GetMapping("/{movieId}")
    public ApiResTemplate<MovieInfoResponseDto> movieFindOne(@PathVariable("movieId") Long movieId) {
        MovieInfoResponseDto movieInfoResponseDto = movieService.movieFindOne(movieId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, movieInfoResponseDto);
    }

    // 영화 수정
    @PatchMapping("/{movieId}")
    public ApiResTemplate<String> movieUpdate(
            @PathVariable("movieId") Long movieId,
            @RequestBody @Valid MovieUpdateRequestDto updateRequestDto) {
        movieService.movieUpdate(movieId, updateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.MOVIE_UPDATE_SUCCESS);
    }

    // 영화 삭제
    @DeleteMapping("/{movieId}")
    public ApiResTemplate<String> movieDelete(@PathVariable("movieId") Long movieId) {
        movieService.movieDelete(movieId);
        return ApiResTemplate.successWithNoContent(SuccessCode.MOVIE_DELETE_SUCCESS);
    }
}
