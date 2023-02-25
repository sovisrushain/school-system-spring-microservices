package com.course.courseservice.service;

import com.course.courseservice.dto.CourseDTO;

import java.util.List;

public interface CourseService {
    String addNewCourse(CourseDTO courseDTO);
    CourseDTO getCourseById(String courseId);
    List<CourseDTO> getAllCourses();
    String deleteCourseById(String courseId);
}
