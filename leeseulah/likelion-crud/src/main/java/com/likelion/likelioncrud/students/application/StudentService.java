package com.likelion.likelioncrud.students.application;

import com.likelion.likelioncrud.lectures.domain.Lecture;
import com.likelion.likelioncrud.lectures.domain.repository.LectureRepository;
import com.likelion.likelioncrud.students.api.dto.request.StudentSaveRequestDto;
import com.likelion.likelioncrud.students.api.dto.request.StudentUpdateRequestDto;
import com.likelion.likelioncrud.students.api.dto.response.StudentInfoResponseDto;
import com.likelion.likelioncrud.students.api.dto.response.StudentListResponseDto;
import com.likelion.likelioncrud.students.domain.Student;
import com.likelion.likelioncrud.students.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {
    private final LectureRepository lectureRepository;
    private final StudentRepository studentRepository;

    // 학생 저장
    @Transactional
    public void studentSave(StudentSaveRequestDto studentSaveRequestDto) {
        Lecture lecture = lectureRepository.findById(studentSaveRequestDto.lectureId()).orElseThrow(IllegalArgumentException::new);

        Student student = Student.builder()
                .name(studentSaveRequestDto.name())
                .grade(studentSaveRequestDto.grade())
                .lecture(lecture)
                .build();

        studentRepository.save(student);
    }

    // 특정 강의를 수강하는 학생 목록을 조회
    public StudentListResponseDto studentFindLecture(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(IllegalArgumentException::new);

        List<Student> students = studentRepository.findByLecture(lecture);
        List<StudentInfoResponseDto> studentInfoResponseDtos = students.stream()
                .map(StudentInfoResponseDto::from)
                .toList();

        return StudentListResponseDto.from(studentInfoResponseDtos);
    }

    // 학생 수정
    @Transactional
    public void studentUpdate(Long studentId,
                           StudentUpdateRequestDto studentUpdateRequestDto) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(IllegalArgumentException::new);

        student.update(studentUpdateRequestDto);
    }

    // 학생 삭제
    @Transactional
    public void studentDelete(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(IllegalArgumentException::new);

        studentRepository.delete(student);
    }
}

