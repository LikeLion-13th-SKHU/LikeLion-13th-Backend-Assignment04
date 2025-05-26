package com.likelion.likelionassignmentcrud.department.domain;

import com.likelion.likelionassignmentcrud.department.api.dto.request.DepartmentUpdateRequestDto;
import com.likelion.likelionassignmentcrud.employee.domain.Employee;
import jakarta.persistence.*;
        import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long departmentId;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Part part;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();

    @Builder
    private Department(String name, Part part) {
        this.name = name;
        this.part = part;
    }

    public void update(DepartmentUpdateRequestDto departmentUpdateRequestDto) {
        this.name = departmentUpdateRequestDto.name();
        this.part = departmentUpdateRequestDto.part();
    }
}
