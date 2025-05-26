package com.likelion.likelionassignmentcrud.department.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.likelion.likelionassignmentcrud.common.error.SuccessCode;
import com.likelion.likelionassignmentcrud.common.template.ApiResTemplate;
import com.likelion.likelionassignmentcrud.department.api.dto.request.DepartmentSaveRequestDto;
import com.likelion.likelionassignmentcrud.department.api.dto.request.DepartmentUpdateRequestDto;
import com.likelion.likelionassignmentcrud.department.api.dto.response.DepartmentInfoResponseDto;
import com.likelion.likelionassignmentcrud.department.api.dto.response.DepartmentListResponseDto;
import com.likelion.likelionassignmentcrud.department.application.DepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    // 부서 저장
    @PostMapping("/save")
    public ApiResTemplate<String> departmentSave(@RequestBody @Valid DepartmentSaveRequestDto departmentSaveRequestDto) {
        departmentService.departmentSave(departmentSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.DEPARTMENT_SAVE_SUCCESS);
    }

    // 부서 전체 조회
    @GetMapping("/all")
    public ApiResTemplate<DepartmentListResponseDto> departmentFindAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size,
            Pageable pageable
    ) {
        // page가 1부터 시작하도록 설정
        Pageable customPageable = PageRequest.of(page - 1, pageable.getPageSize(), pageable.getSort());
        DepartmentListResponseDto departmentListResponseDto = departmentService.departmentFindAll(customPageable);

        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, departmentListResponseDto);
    }

    // 부서 id를 통해 특정 부서 조회
    @GetMapping("/{departmentId}")
    public ApiResTemplate<DepartmentInfoResponseDto> departmentFindOne(@PathVariable("departmentId") Long departmentId) {
        DepartmentInfoResponseDto departmentInfoResponseDto = departmentService.departmentFindOne(departmentId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, departmentInfoResponseDto);
    }

    // 부서 id를 통한 부서 수정
    @PatchMapping("/{departmentId}")
    public ApiResTemplate<String> departmentUpdate(
            @PathVariable("departmentId") Long departmentId,
            @Valid @RequestBody DepartmentUpdateRequestDto departmentUpdateRequestDto) {
        departmentService.departmentUpdate(departmentId, departmentUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.DEPARTMENT_UPDATE_SUCCESS);
    }

    // 부서 id를 통한 삭제
    @DeleteMapping("/{departmentId}")
    public ApiResTemplate<String> departmentDelete(
            @PathVariable("departmentId") Long departmentId) {
        departmentService.departmentDelete(departmentId);
        return ApiResTemplate.successWithNoContent(SuccessCode.DEPARTMENT_DELETE_SUCCESS);
    }
}
