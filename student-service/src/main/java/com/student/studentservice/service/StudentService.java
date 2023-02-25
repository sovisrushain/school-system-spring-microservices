package com.student.studentservice.service;

import com.student.studentservice.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    String saveStudent(StudentDTO studentDTO);
    StudentDTO getStudentById(String studentId);
    List<StudentDTO> findAllStudents();
    String deleteStudentById(String studentId);
}
