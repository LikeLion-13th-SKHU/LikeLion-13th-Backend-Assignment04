package com.kimminwoo.likelionassignmentcrud.assignment.application;

import com.kimminwoo.likelionassignmentcrud.assignment.api.dto.request.AssignmentSaveRequestDto;
import com.kimminwoo.likelionassignmentcrud.assignment.api.dto.request.AssignmentUpdateRequestDto;
import com.kimminwoo.likelionassignmentcrud.assignment.api.dto.response.AssignmentInfoResponseDto;
import com.kimminwoo.likelionassignmentcrud.assignment.api.dto.response.AssignmentListResponseDto;
import com.kimminwoo.likelionassignmentcrud.assignment.domain.Assignment;
import com.kimminwoo.likelionassignmentcrud.assignment.domain.repository.AssignmentRepository;
import com.kimminwoo.likelionassignmentcrud.common.error.ErrorCode;
import com.kimminwoo.likelionassignmentcrud.common.exception.BusinessException;
import com.kimminwoo.likelionassignmentcrud.student.domain.Student;
import com.kimminwoo.likelionassignmentcrud.student.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AssignmentService {

    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;

    @Transactional
    public void saveAssignment(AssignmentSaveRequestDto requestDto) {
        Student student = studentRepository.findById(requestDto.studentId())
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.STUDENT_NOT_FOUND));

        Assignment assignment = Assignment.builder()
                .title(requestDto.title())
                .content(requestDto.content())
                .student(student)
                .build();

        assignmentRepository.save(assignment);
    }

    public AssignmentListResponseDto getAssignmentsByStudentId(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.STUDENT_NOT_FOUND));

        List<Assignment> assignments = assignmentRepository.findAllByStudent(student);

        List<AssignmentInfoResponseDto> assignmentDtoList = assignments.stream()
                .map(AssignmentInfoResponseDto::from)
                .toList();

        return AssignmentListResponseDto.from(assignmentDtoList);
    }

    @Transactional
    public void updateAssignment(Long assignmentId, AssignmentUpdateRequestDto requestDto) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ASSIGNMENT_NOT_FOUND));

        assignment.update(requestDto);
    }

    @Transactional
    public void deleteAssignment(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ASSIGNMENT_NOT_FOUND));

        assignmentRepository.delete(assignment);
    }
}
