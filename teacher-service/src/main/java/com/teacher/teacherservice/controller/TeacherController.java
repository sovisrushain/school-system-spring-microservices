package com.teacher.teacherservice.controller;

import com.teacher.teacherservice.dto.TeacherDTO;
import com.teacher.teacherservice.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public String saveTeacher(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.saveTeacher(teacherDTO);
    }

    @GetMapping("/{teacherId}")
    public TeacherDTO getTeacherById(@PathVariable String teacherId) {
        return teacherService.getTeacherById(teacherId);
    }

    @GetMapping
    public List<TeacherDTO> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @DeleteMapping("/{teacherId}")
    public String deleteTeacherById(@PathVariable String teacherId) {
        return teacherService.deleteTeacherById(teacherId);
    }
}
