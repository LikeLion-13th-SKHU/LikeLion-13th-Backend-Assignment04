package com.likelion.likelioncrud.lectures.domain;

import com.likelion.likelioncrud.lectures.api.dto.request.LectureUpdateRequestDto;
import com.likelion.likelioncrud.students.domain.Student;
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
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long lectureId;

    private String name;

    private String title;

    private String content;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students = new ArrayList<>();

    @Builder
    private Lecture(String name, String title, String content) {
        this.name = name;
        this.title = title;
        this.content = content;
    }

    public void update(LectureUpdateRequestDto lectureUpdateRequestDto) {
        this.name = lectureUpdateRequestDto.name();
        this.title = lectureUpdateRequestDto.title();
        this.content = lectureUpdateRequestDto.content();
    }
}