package com.likelion.springcrud.review.api;

import com.likelion.springcrud.common.error.SuccessCode;
import com.likelion.springcrud.common.template.ApiResponse;
import com.likelion.springcrud.review.api.dto.request.ReviewSaveRequestDto;
import com.likelion.springcrud.review.api.dto.request.ReviewUpdateRequestDto;
import com.likelion.springcrud.review.api.dto.response.ReviewInfoResponseDto;
import com.likelion.springcrud.review.api.dto.response.ReviewListResponseDto;
import com.likelion.springcrud.review.application.ReviewService;
import com.likelion.springcrud.review.domain.Review;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/save")
    public ApiResponse<String> reviewSave(@RequestBody @Valid ReviewSaveRequestDto requestDto) {
        reviewService.saveReview(requestDto);
        return ApiResponse.success(SuccessCode.REVIEW_SAVE_SUCCESS);
    }

    @GetMapping("/member/{id}")
    public ApiResponse<ReviewListResponseDto> reviewFindAll(@PathVariable("id") Long id,
                                                            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable) {
        ReviewListResponseDto reviewListResponseDto = reviewService.reviewFindMember(id, pageable);
        return ApiResponse.success(SuccessCode.GET_SUCCESS, reviewListResponseDto);
    }

    @GetMapping("/{id}")
    public ApiResponse<ReviewInfoResponseDto> reviewFindOne(@PathVariable("id") Long id) {
        ReviewInfoResponseDto reviewInfoResponseDto = reviewService.reviewFindById(id);
        return ApiResponse.success(SuccessCode.GET_SUCCESS, reviewInfoResponseDto);
    }

    // 리뷰 수정
    @PatchMapping("/{id}")
    public ApiResponse<String> reviewUpdate(@PathVariable("id") Long id,
                                                              @RequestBody @Valid ReviewUpdateRequestDto reviewUpdateRequestDto) {
        reviewService.reviewUpdate(id, reviewUpdateRequestDto);
        return ApiResponse.success(SuccessCode.REVIEW_UPDATE_SUCCESS);
    }

    // 리뷰 삭제
    @DeleteMapping("/{id}")
    public ApiResponse<String> reviewDelete(@PathVariable("id") Long id) {
        reviewService.reviewDelete(id);
        return ApiResponse.success(SuccessCode.REVIEW_DELETE_SUCCESS);
    }
}
