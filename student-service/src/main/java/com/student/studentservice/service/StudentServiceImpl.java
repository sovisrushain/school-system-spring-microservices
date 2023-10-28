package com.student.studentservice.service;

import com.student.studentservice.dto.StudentDTO;
import com.student.studentservice.model.StudentDAO;
import com.student.studentservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private static final Logger logger = LogManager.getLogger(StudentServiceImpl.class);

    @Override
    public String saveStudent(StudentDTO studentDTO) {
        logger.info("StudentServiceImpl.class: saveStudent(): start");
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
        logger.info("StudentServiceImpl.class: getStudentById(): start");
        Optional<StudentDAO> student = studentRepository.findById(studentId);
        return StudentDTO.builder()
                .studentId(student.get().getStudentId())
                .teacherId(student.get().getTeacherId())
                .studentName(student.get().getStudentName())
                .build();
    }

    @Override
    public List<StudentDTO> findAllStudents() {
        logger.info("StudentServiceImpl.class: findAllStudents(): start");
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
        logger.info("StudentServiceImpl.class: deleteStudentById(): start");
        studentRepository.deleteById(studentId);
        return studentId;
    }
}
