package com.kimminwoo.likelionassignmentcrud.assignment.domain;


import com.kimminwoo.likelionassignmentcrud.assignment.api.dto.request.AssignmentUpdateRequestDto;
import com.kimminwoo.likelionassignmentcrud.student.domain.Student;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Long assignmentId;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @Builder
    private Assignment(String title, String content, Student student) {
        this.title = title;
        this.content = content;
        this.student = student;
    }
    public void update(AssignmentUpdateRequestDto assignmentUpdateRequestDto) {
        this.title = assignmentUpdateRequestDto.title();
        this.content = assignmentUpdateRequestDto.content();
    }
}
