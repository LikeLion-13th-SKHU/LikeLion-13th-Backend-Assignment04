package com.likelion.likelionassignmentcrud.department.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
public record DepartmentListResponseDto(
        List<DepartmentInfoResponseDto> departments
) {
    public static DepartmentListResponseDto from(List<DepartmentInfoResponseDto> departments) {
        return DepartmentListResponseDto.builder()
                .departments(departments)
                .build();
    }
}
