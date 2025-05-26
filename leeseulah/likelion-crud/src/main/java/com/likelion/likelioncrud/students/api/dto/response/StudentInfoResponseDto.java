package com.likelion.likelioncrud.students.api.dto.response;

import com.likelion.likelioncrud.students.domain.Student;
import lombok.Builder;

@Builder
public record StudentInfoResponseDto(
        String name,
        int grade,
        String lectureName
) {
    public static StudentInfoResponseDto from(Student student) {
        return StudentInfoResponseDto.builder()
                .name(student.getName())
                .grade(student.getGrade())
                .lectureName(student.getLecture().getTitle())
                .build();
    }
}