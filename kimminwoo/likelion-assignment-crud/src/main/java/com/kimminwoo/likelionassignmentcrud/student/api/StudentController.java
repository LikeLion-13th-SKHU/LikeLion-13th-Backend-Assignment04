package com.kimminwoo.likelionassignmentcrud.student.api;

import com.kimminwoo.likelionassignmentcrud.common.error.SuccessCode;
import com.kimminwoo.likelionassignmentcrud.common.template.ApiResTemplate;
import com.kimminwoo.likelionassignmentcrud.student.api.dto.request.StudentSaveRequestDto;
import com.kimminwoo.likelionassignmentcrud.student.api.dto.request.StudentUpdateRequestDto;
import com.kimminwoo.likelionassignmentcrud.student.api.dto.response.StudentInfoResponseDto;
import com.kimminwoo.likelionassignmentcrud.student.api.dto.response.StudentListResponseDto;
import com.kimminwoo.likelionassignmentcrud.student.application.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    // 학생 저장
    @PostMapping
    public ResponseEntity<ApiResTemplate<Void>> saveStudent(@RequestBody @Valid StudentSaveRequestDto requestDto) {
        studentService.saveStudent(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResTemplate.successWithNoContent(SuccessCode.STUDENT_SAVE_SUCCESS));
    }

    // 전체 학생 조회
    @GetMapping
    public ResponseEntity<ApiResTemplate<StudentListResponseDto>> getAllStudents() {
        StudentListResponseDto response = studentService.getAllStudents();
        return ResponseEntity.ok(ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, response));
    }

    // 개별 학생 조회
    @GetMapping("/{studentId}")
    public ResponseEntity<ApiResTemplate<StudentInfoResponseDto>> getStudentById(@PathVariable Long studentId) {
        StudentInfoResponseDto response = studentService.getStudentById(studentId);
        return ResponseEntity.ok(ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, response));
    }

    // 학생 수정
    @PutMapping("/{studentId}")
    public ResponseEntity<ApiResTemplate<Void>> updateStudent(
            @PathVariable Long studentId,
            @RequestBody @Valid StudentUpdateRequestDto requestDto) {
        studentService.updateStudent(studentId, requestDto);
        return ResponseEntity.ok(ApiResTemplate.successWithNoContent(SuccessCode.STUDENT_UPDATE_SUCCESS));
    }

    // 학생 삭제
    @DeleteMapping("/{studentId}")
    public ResponseEntity<ApiResTemplate<Void>> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok(ApiResTemplate.successWithNoContent(SuccessCode.STUDENT_DELETE_SUCCESS));
    }
}
