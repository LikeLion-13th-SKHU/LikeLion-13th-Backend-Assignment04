package com.likelion.likelionassignmentcrud.employee.api.dto.response;

import com.likelion.likelionassignmentcrud.department.domain.Part;
import com.likelion.likelionassignmentcrud.employee.domain.Employee;
import lombok.Builder;

@Builder
public record EmployeeInfoResponseDto(
        String name,
        int age,
        String responsibilities,
        Part part
) {
    public static EmployeeInfoResponseDto from(Employee employee) {
        return EmployeeInfoResponseDto.builder()
                .name(employee.getName())
                .age(employee.getAge())
                .responsibilities(employee.getResponsibilities())
                .part(employee.getDepartment().getPart())
                .build();
    }
}
