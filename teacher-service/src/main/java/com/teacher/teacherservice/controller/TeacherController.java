package com.teacher.teacherservice.controller;

import com.teacher.teacherservice.dto.TeacherDTO;
import com.teacher.teacherservice.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    private static final Logger logger = LogManager.getLogger(TeacherController.class);

    @PostMapping
    public String saveTeacher(@RequestBody TeacherDTO teacherDTO) {
        logger.info("TeacherController.class: saveTeacher(): start");
        return teacherService.saveTeacher(teacherDTO);
    }

    @GetMapping("/{teacherId}")
    public TeacherDTO getTeacherById(@PathVariable String teacherId) {
        logger.info("TeacherController.class: getTeacherById(): start");
        return teacherService.getTeacherById(teacherId);
    }

    @GetMapping
    public List<TeacherDTO> getAllTeachers() {
        logger.info("TeacherController.class: getAllTeachers(): start");
        return teacherService.getAllTeachers();
    }

    @DeleteMapping("/{teacherId}")
    public String deleteTeacherById(@PathVariable String teacherId) {
        logger.info("TeacherController.class: deleteTeacherById(): start");
        return teacherService.deleteTeacherById(teacherId);
    }
}
