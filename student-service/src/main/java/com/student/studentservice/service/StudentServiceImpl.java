package com.student.studentservice.service;

import com.student.studentservice.dto.StudentDTO;
import com.student.studentservice.model.StudentDAO;
import com.student.studentservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public String saveStudent(StudentDTO studentDTO) {
        StudentDAO student = StudentDAO.builder()
                .studentId(studentDTO.getStudentId())
                .teacherId(studentDTO.getTeacherId())
                .studentName(studentDTO.getStudentName())
                .build();
        studentRepository.save(student);
        return student.getStudentId();
    }

    @Override
    public StudentDTO getStudentById(String studentId) {
        Optional<StudentDAO> student = studentRepository.findById(studentId);
        return StudentDTO.builder()
                .studentId(student.get().getStudentId())
                .teacherId(student.get().getTeacherId())
                .studentName(student.get().getStudentName())
                .build();
    }

    @Override
    public List<StudentDTO> findAllStudents() {
        List<StudentDAO> studentList = studentRepository.findAll();
        return studentList.stream().map(student -> StudentDTO.builder().
                studentId(student.getStudentId())
                .teacherId(student.getTeacherId())
                .studentName(student.getStudentName())
                .build())
                .toList();
    }

    @Override
    public String deleteStudentById(String studentId) {
        studentRepository.deleteById(studentId);
        return studentId;
    }
}
