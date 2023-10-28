package com.student.studentservice.controller;

import com.student.studentservice.dto.StudentDTO;
import com.student.studentservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    private static final Logger logger = LogManager.getLogger(StudentController.class);

    @PostMapping
    public String saveStudent(@RequestBody StudentDTO studentDTO) {
        logger.info("StudentController.class: saveStudent(): start");
        return studentService.saveStudent(studentDTO);
    }

    @GetMapping("/{studentId}")
    public StudentDTO getStudentById(@PathVariable String studentId) {
        logger.info("StudentController.class: getStudentById(): start");
        return studentService.getStudentById(studentId);
    }

    @GetMapping
    public List<StudentDTO> findAllStudents() {
        logger.info("StudentController.class: findAllStudents(): start");
        return studentService.findAllStudents();
    }

    @DeleteMapping("/{studentId}")
    public String deleteStudentById(@PathVariable String studentId) {
        logger.info("StudentController.class: deleteStudentById(): start");
        return studentService.deleteStudentById(studentId);
    }
}
