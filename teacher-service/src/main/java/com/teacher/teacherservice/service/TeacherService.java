package com.teacher.teacherservice.service;

import com.teacher.teacherservice.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {
    String saveTeacher(TeacherDTO teacherDTO);
    TeacherDTO getTeacherById(String teacherId);
    List<TeacherDTO> getAllTeachers();
    String deleteTeacherById(String teacherId);
}
