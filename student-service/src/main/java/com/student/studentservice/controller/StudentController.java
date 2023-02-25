package com.student.studentservice.controller;

import com.student.studentservice.dto.StudentDTO;
import com.student.studentservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public String saveStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.saveStudent(studentDTO);
    }

    @GetMapping("/{studentId}")
    public StudentDTO getStudentById(@PathVariable String studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping
    public List<StudentDTO> findAllStudents() {
        return studentService.findAllStudents();
    }

    @DeleteMapping("/{studentId}")
    public String deleteStudentById(@PathVariable String studentId) {
        return studentService.deleteStudentById(studentId);
    }
}
