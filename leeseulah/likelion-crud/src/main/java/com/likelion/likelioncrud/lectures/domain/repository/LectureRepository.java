package com.likelion.likelioncrud.lectures.domain.repository;

import com.likelion.likelioncrud.lectures.domain.Lecture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Page<Lecture> findAll(Pageable pageable);
}