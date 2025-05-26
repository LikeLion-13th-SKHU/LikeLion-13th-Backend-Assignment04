package com.likelion.likelionassignmentcrud.department.application;

import com.likelion.likelionassignmentcrud.common.error.ErrorCode;
import com.likelion.likelionassignmentcrud.common.exception.BusinessException;
import com.likelion.likelionassignmentcrud.department.api.dto.request.DepartmentSaveRequestDto;
import com.likelion.likelionassignmentcrud.department.api.dto.request.DepartmentUpdateRequestDto;
import com.likelion.likelionassignmentcrud.department.api.dto.response.DepartmentInfoResponseDto;
import com.likelion.likelionassignmentcrud.department.api.dto.response.DepartmentListResponseDto;
import com.likelion.likelionassignmentcrud.department.domain.Department;
import com.likelion.likelionassignmentcrud.department.domain.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    
    // 부서 정보 저장
    @Transactional
    public void departmentSave(DepartmentSaveRequestDto departmentSaveRequestDto) {
        Department department = Department.builder()
                .name(departmentSaveRequestDto.name())
                .part(departmentSaveRequestDto.part())
                .build();
        departmentRepository.save(department);
    }
    
    // 부서 모두 조회
    public DepartmentListResponseDto departmentFindAll(Pageable pageable) {
        Page<Department> departments = departmentRepository.findAll(pageable);

        List<DepartmentInfoResponseDto> departmentInfoResponseDtoList = departments.stream()
                .map(DepartmentInfoResponseDto::from)
                .toList();
        return DepartmentListResponseDto.from(departmentInfoResponseDtoList);
    }
    
    // 단일 부서 조회
    public DepartmentInfoResponseDto departmentFindOne(Long departmentId) {
        Department department = departmentRepository
                .findById(departmentId)
                .orElseThrow(
                        // 조회하려고 하는 부서 id가 없을 때 발생하는 예외 처리
                        () -> new BusinessException(ErrorCode.DEPARTMENT_NOT_FOUND_EXCEPTION,
                                "id가 " + departmentId + "인 " + ErrorCode.DEPARTMENT_NOT_FOUND_EXCEPTION.getMessage())
                );
        return DepartmentInfoResponseDto.from(department);
    }

    // 부서 정보 수정
    @Transactional
    public void departmentUpdate(Long departmentId, DepartmentUpdateRequestDto departmentUpdateRequestDto) {
        Department department = getDepartment(departmentId);
        department.update(departmentUpdateRequestDto);
    }

    // 부서 정보 삭제
    @Transactional
    public void departmentDelete(Long departmentId) {
        Department department = getDepartment(departmentId);
        departmentRepository.delete(department);
    }

    // 엔티티 찾는 공통 메소드
    private Department getDepartment(Long departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(
                () -> new BusinessException(ErrorCode.DEPARTMENT_NOT_FOUND_EXCEPTION,
                        "id가 " + departmentId + "인 " + ErrorCode.DEPARTMENT_NOT_FOUND_EXCEPTION.getMessage())
        );
    }

}
