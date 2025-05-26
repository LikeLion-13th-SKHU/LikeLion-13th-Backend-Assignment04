package com.kimminwoo.likelionassignmentcrud.student.application;

import com.kimminwoo.likelionassignmentcrud.common.error.ErrorCode;
import com.kimminwoo.likelionassignmentcrud.common.exception.BusinessException;
import com.kimminwoo.likelionassignmentcrud.student.api.dto.request.StudentSaveRequestDto;
import com.kimminwoo.likelionassignmentcrud.student.api.dto.request.StudentUpdateRequestDto;
import com.kimminwoo.likelionassignmentcrud.student.api.dto.response.StudentInfoResponseDto;
import com.kimminwoo.likelionassignmentcrud.student.api.dto.response.StudentListResponseDto;
import com.kimminwoo.likelionassignmentcrud.student.domain.Student;
import com.kimminwoo.likelionassignmentcrud.student.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public void saveStudent(StudentSaveRequestDto studentSaveRequestDto) {
        Student student = Student.builder()
                .name(studentSaveRequestDto.name())
                .phoneNumber(studentSaveRequestDto.phoneNumber())
                .build();

        studentRepository.save(student);
    }

    public StudentListResponseDto getAllStudents() {
        List<Student> students = studentRepository.findAll();

        List<StudentInfoResponseDto> studentInfoList = students.stream()
                .map(StudentInfoResponseDto::from)
                .toList();

        return StudentListResponseDto.from(studentInfoList);
    }

    public StudentInfoResponseDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.STUDENT_NOT_FOUND));

        return StudentInfoResponseDto.from(student);
    }
    @Transactional
    public void updateStudent(Long id, StudentUpdateRequestDto requestDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.STUDENT_NOT_FOUND));

        student.update(requestDto.name(), requestDto.phoneNumber());
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.STUDENT_NOT_FOUND));

        studentRepository.delete(student);
    }

}
