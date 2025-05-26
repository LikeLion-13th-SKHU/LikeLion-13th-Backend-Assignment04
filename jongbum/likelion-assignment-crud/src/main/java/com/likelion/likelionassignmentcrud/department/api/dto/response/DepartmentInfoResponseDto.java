package com.likelion.likelionassignmentcrud.department.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.likelion.likelionassignmentcrud.department.domain.Department;
import com.likelion.likelionassignmentcrud.department.domain.Part;
import lombok.Builder;

@Builder
public record DepartmentInfoResponseDto(
        String name,
        Part part
) {
    public static DepartmentInfoResponseDto from(Department department) {
        return DepartmentInfoResponseDto.builder()
                .name(department.getName())
                .part(department.getPart())
                .build();
    }
}
