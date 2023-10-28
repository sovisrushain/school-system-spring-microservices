package com.course.courseservice.controller;

import com.course.courseservice.dto.CourseDTO;
import com.course.courseservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    private static final Logger logger = LogManager.getLogger(CourseController.class);

    @PostMapping("/")
    public String addNewCourse(@RequestBody CourseDTO courseDTO) {
        logger.info("CourseController.class: addNewCourse(): start");
        return courseService.addNewCourse(courseDTO);
    }

    @GetMapping("/{courseId}")
    public CourseDTO getCourseById(@PathVariable String courseId) {
        logger.info("CourseController.class: getCourseById(): start");
        return courseService.getCourseById(courseId);
    }

    @GetMapping("/")
    public List<CourseDTO> getAllCourses() {
        logger.info("CourseController.class: getAllCourses(): start");
        return courseService.getAllCourses();
    }

    @DeleteMapping("/{courseId}")
    public String deleteCourseById(@PathVariable String courseId) {
        logger.info("CourseController.class: deleteCourseById(): start");
        return courseService.deleteCourseById(courseId);
    }

}

