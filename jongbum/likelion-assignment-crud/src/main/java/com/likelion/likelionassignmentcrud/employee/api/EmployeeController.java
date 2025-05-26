package com.likelion.likelionassignmentcrud.employee.api;

import com.likelion.likelionassignmentcrud.common.error.SuccessCode;
import com.likelion.likelionassignmentcrud.common.template.ApiResTemplate;
import com.likelion.likelionassignmentcrud.employee.api.dto.request.EmployeeSaveRequestDto;
import com.likelion.likelionassignmentcrud.employee.api.dto.request.EmployeeUpdateRequestDto;
import com.likelion.likelionassignmentcrud.employee.api.dto.response.EmployeeInfoResponseDto;
import com.likelion.likelionassignmentcrud.employee.api.dto.response.EmployeeListResponseDto;
import com.likelion.likelionassignmentcrud.employee.application.EmployeeService;
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
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    // 직원 저장
    @PostMapping("/save")
    public ApiResTemplate<String> employeeSave(@RequestBody @Valid EmployeeSaveRequestDto employeeSaveRequestDto) {
        employeeService.employeeSave(employeeSaveRequestDto);

        return ApiResTemplate.successWithNoContent(SuccessCode.EMPLOYEE_SAVE_SUCCESS);
    }

    // 부서 id를 기준으로 해당 부서의 직원 조회
    @GetMapping("/{departmentId}")
    public ApiResTemplate<EmployeeListResponseDto> itsEmployeeFindAll(
            @PathVariable("departmentId") Long departmentId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size,
            Pageable pageable
            ) {
        // page가 1부터 시작하도록 설정
        Pageable customPageable = PageRequest.of(page - 1, pageable.getPageSize(), pageable.getSort());
        EmployeeListResponseDto employeeListResponseDto = employeeService.employeeFindDepartment(departmentId, customPageable);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, employeeListResponseDto);
    }

    // 직원 id를 통한 직원 수정
    @PatchMapping("/{employeeId}")
    public ApiResTemplate<String> employeeUpdate(
            @PathVariable("employeeId") Long employeeId,
            @RequestBody @Valid EmployeeUpdateRequestDto employeeUpdateRequestDto) {
        employeeService.employeeUpdate(employeeId, employeeUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.EMPLOYEE_UPDATE_SUCCESS);
    }

    // 직원 id를 기준으로 직원 삭제
    @DeleteMapping("/{employeeId}")
    public ApiResTemplate<String> employeeDelete(
            @PathVariable("employeeId") Long employeeId) {
        employeeService.employeeDelete(employeeId);
        return ApiResTemplate.successWithNoContent(SuccessCode.EMPLOYEE_DELETE_SUCCESS);
    }
}
