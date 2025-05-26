package com.likelion.likelioncrud.students.domain.repository;

import com.likelion.likelioncrud.lectures.domain.Lecture;
import com.likelion.likelioncrud.students.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByLecture(Lecture lecture);
}