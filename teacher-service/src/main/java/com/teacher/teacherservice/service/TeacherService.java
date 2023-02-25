package com.teacher.teacherservice.service;

import com.teacher.teacherservice.dto.TeacherDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TeacherService {
    String saveTeacher(TeacherDTO teacherDTO);
    TeacherDTO getTeacherById(String teacherId);
    List<TeacherDTO> getAllTeachers();
    String deleteTeacherById(String teacherId);
}
