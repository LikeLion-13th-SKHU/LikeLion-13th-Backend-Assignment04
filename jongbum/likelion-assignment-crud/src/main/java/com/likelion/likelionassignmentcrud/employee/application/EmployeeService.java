package com.likelion.likelionassignmentcrud.employee.application;

import com.likelion.likelionassignmentcrud.common.error.ErrorCode;
import com.likelion.likelionassignmentcrud.common.exception.BusinessException;
import com.likelion.likelionassignmentcrud.department.domain.Department;
import com.likelion.likelionassignmentcrud.department.domain.repository.DepartmentRepository;
import com.likelion.likelionassignmentcrud.employee.api.dto.request.EmployeeSaveRequestDto;
import com.likelion.likelionassignmentcrud.employee.api.dto.request.EmployeeUpdateRequestDto;
import com.likelion.likelionassignmentcrud.employee.api.dto.response.EmployeeInfoResponseDto;
import com.likelion.likelionassignmentcrud.employee.api.dto.response.EmployeeListResponseDto;
import com.likelion.likelionassignmentcrud.employee.domain.Employee;
import com.likelion.likelionassignmentcrud.employee.domain.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    // 직원 저장
    @Transactional
    public void employeeSave(EmployeeSaveRequestDto employeeSaveRequestDto) {
        Department department = departmentRepository.findById(employeeSaveRequestDto.departmentId()).orElseThrow(
                () -> new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR,
                        ErrorCode.INTERNAL_SERVER_ERROR.getMessage())
        );

        Employee employee = Employee.builder()
                .name(employeeSaveRequestDto.name())
                .age(employeeSaveRequestDto.age())
                .responsibilities(employeeSaveRequestDto.responsibilities())
                .department(department)
                .build();

        employeeRepository.save(employee);
    }

    // 특정 부서의 직원 목록 조회
    public EmployeeListResponseDto employeeFindDepartment(Long departmentId, Pageable pageable) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                // 조회하려고 하는 부서 id가 없을 때 발생하는 예외 처리
                () -> new BusinessException(ErrorCode.DEPARTMENT_NOT_FOUND_EXCEPTION,
                        "id가 " + departmentId + "인 " + ErrorCode.DEPARTMENT_NOT_FOUND_EXCEPTION.getMessage())
        );

        Page<Employee> employees = employeeRepository.findByDepartment(department, pageable);
        List<EmployeeInfoResponseDto> employeeInfoResponseDtos = employees.stream()
                .map(EmployeeInfoResponseDto::from)
                .toList();

        return EmployeeListResponseDto.from(employeeInfoResponseDtos);
    }


    // 직원 수정
    @Transactional
    public void employeeUpdate(Long employeeId, EmployeeUpdateRequestDto employeeUpdateRequestDto) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                // 조회하려고 하는 직원 id가 없을 때 발생하는 예외 처리
                () -> new BusinessException(ErrorCode.EMPLOYEE_NOT_FOUND_EXCEPTION,
                        "id가 " + employeeId + "인 " + ErrorCode.EMPLOYEE_NOT_FOUND_EXCEPTION.getMessage())
        );

        employee.update(employeeUpdateRequestDto);
    }

    // 직원 삭제
    @Transactional
    public void employeeDelete(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                // 조회하려고 하는 직원 id가 없을 때 발생하는 예외 처리
                () -> new BusinessException(ErrorCode.EMPLOYEE_NOT_FOUND_EXCEPTION,
                        "id가 " + employeeId + "인 " + ErrorCode.EMPLOYEE_NOT_FOUND_EXCEPTION.getMessage())
        );

        employeeRepository.delete(employee);
    }
}
