package com.likelion.likelioncrud.lectures.application;

import java.util.List;

import com.likelion.likelioncrud.common.error.ErrorCode;
import com.likelion.likelioncrud.common.exception.BusinessException;
import com.likelion.likelioncrud.lectures.api.dto.request.LectureSaveRequestDto;
import com.likelion.likelioncrud.lectures.api.dto.request.LectureUpdateRequestDto;
import com.likelion.likelioncrud.lectures.api.dto.response.LectureInfoResponseDto;
import com.likelion.likelioncrud.lectures.api.dto.response.LectureListResponseDto;
import com.likelion.likelioncrud.lectures.domain.Lecture;
import com.likelion.likelioncrud.lectures.domain.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LectureService {
    private final LectureRepository lectureRepository;

    // 강의 정보 저장
    @Transactional
    public void lectureSave(LectureSaveRequestDto lectureSaveRequestDto) {
        Lecture lecture = Lecture.builder()
                .name(lectureSaveRequestDto.name())
                .title(lectureSaveRequestDto.title())
                .content(lectureSaveRequestDto.content())
                .build();
        lectureRepository.save(lecture);
    }

    // 강의 모두 조회
    public LectureListResponseDto lectureFindAll(Pageable pageable) {
        Page<Lecture> lectures = lectureRepository.findAll(pageable); // 기존 List -> Page
        List<LectureInfoResponseDto> lectureInfoResponseDtoList = lectures.stream()
                .map(LectureInfoResponseDto::from)
                .toList();
        return LectureListResponseDto.from(lectureInfoResponseDtoList);
    }

    // 단일 강의 조회
    public LectureInfoResponseDto lectureFindOne(Long lectureId) {
        Lecture lecture = lectureRepository
                .findById(lectureId)
                .orElseThrow(
                        () -> new BusinessException(ErrorCode.LECTURE_NOT_FOUND_EXCEPTION,
                                ErrorCode.LECTURE_NOT_FOUND_EXCEPTION.getMessage() + lectureId)
                );
        return LectureInfoResponseDto.from(lecture);
    }

    // 강의 정보 수정
    @Transactional
    public void lectureUpdate(Long lectureId,
                              LectureUpdateRequestDto lectureUpdateRequestDto) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(IllegalArgumentException::new);

        lecture.update(lectureUpdateRequestDto);
    }

    // 강의 정보 삭제
    @Transactional
    public void lectureDelete(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(IllegalArgumentException::new);

        lectureRepository.delete(lecture);
    }
}