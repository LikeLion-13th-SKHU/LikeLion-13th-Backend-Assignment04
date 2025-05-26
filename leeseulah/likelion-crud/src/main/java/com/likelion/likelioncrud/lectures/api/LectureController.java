package com.likelion.likelioncrud.lectures.api;

import com.likelion.likelioncrud.common.error.SuccessCode;
import com.likelion.likelioncrud.common.template.ApiResTemplate;
import com.likelion.likelioncrud.lectures.api.dto.request.LectureSaveRequestDto;
import com.likelion.likelioncrud.lectures.api.dto.request.LectureUpdateRequestDto;
import com.likelion.likelioncrud.lectures.api.dto.response.LectureInfoResponseDto;
import com.likelion.likelioncrud.lectures.api.dto.response.LectureListResponseDto;
import com.likelion.likelioncrud.lectures.application.LectureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final LectureService lectureService;

    // 강의 저장
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResTemplate<String> lectureSave(@RequestBody @Valid LectureSaveRequestDto lectureSaveRequestDto) {
        lectureService.lectureSave(lectureSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.LECTURE_SAVE_SUCCESS);
    }

    // 강의 전체 조회
    @GetMapping("/all")
    public ApiResTemplate<LectureListResponseDto> lectureFindAll(
            @PageableDefault(size = 10, sort = "lectureId", direction = Sort.Direction.ASC)
            Pageable pageable) {
        LectureListResponseDto lectureListResponseDto = lectureService.lectureFindAll(pageable);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, lectureListResponseDto);
    }

    // 강의 id를 통해 특정 강의 정보 조회
    @GetMapping("/{lectureId}")
    public ApiResTemplate<LectureInfoResponseDto> lectureFindOne(@PathVariable("lectureId") Long lectureId) {
        LectureInfoResponseDto lectureInfoResponseDto = lectureService.lectureFindOne(lectureId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, lectureInfoResponseDto);
    }

    // 강의 id를 통한 강의 정보 수정
    @PatchMapping("/{lectureId}")
    public ApiResTemplate<String> lectureUpdate(@PathVariable("lectureId") Long lectureId,
                                               @RequestBody LectureUpdateRequestDto lectureUpdateRequestDto) {
        lectureService.lectureUpdate(lectureId, lectureUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.LECTURE_UPDATE_SUCCESS);
    }
    // 강의 id를 통한 강의 삭제
    @DeleteMapping("/{lectureId}")
    public ApiResTemplate<String> lectureDelete(@PathVariable("lectureId") Long lectureId) {
        lectureService.lectureDelete(lectureId);
        return ApiResTemplate.successWithNoContent(SuccessCode.LECTURE_DELETE_SUCCESS);
    }
}