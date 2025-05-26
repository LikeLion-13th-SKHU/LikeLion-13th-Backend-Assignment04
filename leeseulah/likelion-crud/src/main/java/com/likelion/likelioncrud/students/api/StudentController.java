package com.likelion.likelioncrud.students.api;

import com.likelion.likelioncrud.common.error.SuccessCode;
import com.likelion.likelioncrud.common.template.ApiResTemplate;
import com.likelion.likelioncrud.students.api.dto.request.StudentSaveRequestDto;
import com.likelion.likelioncrud.students.api.dto.request.StudentUpdateRequestDto;
import com.likelion.likelioncrud.students.api.dto.response.StudentListResponseDto;
import com.likelion.likelioncrud.students.application.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    // 학생 저장
    @PostMapping("/save")
    public ApiResTemplate<String> studentSave(@RequestBody @Valid StudentSaveRequestDto studentSaveRequestDto) {
        studentService.studentSave(studentSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.STUDENT_SAVE_SUCCESS);
    }

    // 강의 id를 기준으로 해당 강의를 수강한 학생 목록 조회
    @GetMapping("/{lectureId}")
    public ApiResTemplate<StudentListResponseDto> myStudentFindAll(@PathVariable("lectureId") Long lectureId) {
        StudentListResponseDto studentListResponseDto = studentService.studentFindLecture(lectureId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, studentListResponseDto);
    }

    // 학생 id를 기준으로 학생 정보 수정
    @PatchMapping("/{studentId}")
    public ApiResTemplate<String> studentUpdate(@PathVariable("studentId") Long studentId,
                                             @RequestBody StudentUpdateRequestDto studentUpdateRequestDto) {
        studentService.studentUpdate(studentId, studentUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.STUDENT_UPDATE_SUCCESS);
    }

    // 학생 id를 기준으로 해당 학생 정보 삭제
    @DeleteMapping("/{studentId}")
    public ApiResTemplate<String> studentDelete(@PathVariable("studentId") Long studentId) {
        studentService.studentDelete(studentId);
        return ApiResTemplate.successWithNoContent(SuccessCode.STUDENT_DELETE_SUCCESS);
    }
}