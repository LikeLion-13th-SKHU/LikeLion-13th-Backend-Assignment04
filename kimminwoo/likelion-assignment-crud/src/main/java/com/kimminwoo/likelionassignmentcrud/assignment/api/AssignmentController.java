package com.kimminwoo.likelionassignmentcrud.assignment.api;

import com.kimminwoo.likelionassignmentcrud.assignment.api.dto.request.AssignmentSaveRequestDto;
import com.kimminwoo.likelionassignmentcrud.assignment.api.dto.request.AssignmentUpdateRequestDto;
import com.kimminwoo.likelionassignmentcrud.assignment.api.dto.response.AssignmentListResponseDto;
import com.kimminwoo.likelionassignmentcrud.assignment.application.AssignmentService;
import com.kimminwoo.likelionassignmentcrud.common.error.SuccessCode;
import com.kimminwoo.likelionassignmentcrud.common.template.ApiResTemplate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    // 과제 저장
    @PostMapping
    public ResponseEntity<ApiResTemplate<Void>> saveAssignment(@RequestBody @Valid AssignmentSaveRequestDto requestDto) {
        assignmentService.saveAssignment(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResTemplate.successWithNoContent(SuccessCode.ASSIGNMENT_SAVE_SUCCESS));
    }

    // 학생별 과제 전체 조회
    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResTemplate<AssignmentListResponseDto>> getAssignmentsByStudentId(@PathVariable Long studentId) {
        AssignmentListResponseDto result = assignmentService.getAssignmentsByStudentId(studentId);
        return ResponseEntity.ok(ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, result));
    }

    // 과제 수정
    @PutMapping("/{assignmentId}")
    public ResponseEntity<ApiResTemplate<Void>> updateAssignment(@PathVariable Long assignmentId,
                                                                 @RequestBody @Valid AssignmentUpdateRequestDto requestDto) {
        assignmentService.updateAssignment(assignmentId, requestDto);
        return ResponseEntity.ok(ApiResTemplate.successWithNoContent(SuccessCode.ASSIGNMENT_UPDATE_SUCCESS));
    }

    // 과제 삭제
    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<ApiResTemplate<Void>> deleteAssignment(@PathVariable Long assignmentId) {
        assignmentService.deleteAssignment(assignmentId);
        return ResponseEntity.ok(ApiResTemplate.successWithNoContent(SuccessCode.ASSIGNMENT_DELETE_SUCCESS));
    }
}
