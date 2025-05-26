package com.likelion.likelionassignmentcrud.employee.domain;

import com.likelion.likelionassignmentcrud.department.domain.Department;
import com.likelion.likelionassignmentcrud.employee.api.dto.request.EmployeeUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    private String name;

    private int age;

    private String responsibilities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Builder
    private Employee(String name, int age, String responsibilities, Department department) {
        this.name = name;
        this.age = age;
        this.responsibilities = responsibilities;
        this.department = department;
    }

    // 업데이트하는 메서드
    public void update(EmployeeUpdateRequestDto employeeUpdateRequestDto) {
        this.name = employeeUpdateRequestDto.name();
        this.age = employeeUpdateRequestDto.age();
        this.responsibilities = employeeUpdateRequestDto.responsibilities();
    }
}
