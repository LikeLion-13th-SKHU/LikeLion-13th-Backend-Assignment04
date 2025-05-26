package com.likelion.likelionassignmentcrud.employee.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record EmployeeListResponseDto(
        List<EmployeeInfoResponseDto> employees
) {
    public static EmployeeListResponseDto from(List<EmployeeInfoResponseDto> employees) {
        return EmployeeListResponseDto.builder()
                .employees(employees)
                .build();
    }
}
