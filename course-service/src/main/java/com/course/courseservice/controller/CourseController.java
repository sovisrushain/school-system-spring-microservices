package com.course.courseservice.controller;

import com.course.courseservice.dto.CourseDTO;
import com.course.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public String addNewCourse(@RequestBody CourseDTO courseDTO) {
        return courseService.addNewCourse(courseDTO);
    }

    @GetMapping("/{courseId}")
    public CourseDTO getCourseById(@PathVariable String courseId) {
        return courseService.getCourseById(courseId);
    }

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @DeleteMapping("/{courseId}")
    public String deleteCourseById(@PathVariable String courseId) {
        return courseService.deleteCourseById(courseId);
    }

}

