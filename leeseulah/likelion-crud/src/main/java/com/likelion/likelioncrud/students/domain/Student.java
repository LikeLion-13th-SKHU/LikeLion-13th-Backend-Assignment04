package com.likelion.likelioncrud.students.domain;

import com.likelion.likelioncrud.lectures.domain.Lecture;
import com.likelion.likelioncrud.students.api.dto.request.StudentUpdateRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    private String name;

    private int grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lectue_id")
    private Lecture lecture;

    @Builder
    private Student(String name, int grade, Lecture lecture) {
        this.name = name;
        this.grade = grade;
        this.lecture = lecture;
    }

    public void update(StudentUpdateRequestDto studentUpdateRequestDto) {
        this.name = studentUpdateRequestDto.name();
        this.grade = studentUpdateRequestDto.grade();
    }
}