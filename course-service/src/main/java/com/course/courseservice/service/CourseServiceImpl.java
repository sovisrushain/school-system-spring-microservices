package com.course.courseservice.service;

import com.course.courseservice.dto.CourseDTO;
import com.course.courseservice.exception.ResourceNotFoundException;
import com.course.courseservice.model.CourseDAO;
import com.course.courseservice.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private static final Logger logger = LogManager.getLogger(CourseServiceImpl.class);

    @Override
    public String addNewCourse(CourseDTO courseDTO) {
        logger.info("CourseServiceImpl.class: addNewCourse(): start");
        CourseDAO courseDAO = CourseDAO.builder()
                .courseId(courseDTO.getCourseId())
                .courseName(courseDTO.getCourseName())
                .courseDuration(courseDTO.getCourseDuration())
                .build();
        courseRepository.save(courseDAO);
        return courseDAO.getCourseId();
    }

    @Override
    public CourseDTO getCourseById(String courseId) {
        logger.info("CourseServiceImpl.class: getCourseById(): start");
        Optional<CourseDAO> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new ResourceNotFoundException("No course is associated with the provided courseId");
        }
        return CourseDTO.builder()
                .courseId(course.get().getCourseId())
                .courseName(course.get().getCourseName())
                .courseDuration(course.get().getCourseDuration())
                .build();
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        logger.info("CourseServiceImpl.class: getAllCourses(): start");
        List<CourseDAO> courseDAOList = courseRepository.findAll();
        return courseDAOList.stream().map(courseDAO -> CourseDTO.builder()
                .courseId(courseDAO.getCourseId())
                .courseName(courseDAO.getCourseName())
                .courseDuration(courseDAO.getCourseDuration())
                .build())
                .toList();
    }

    @Override
    public String deleteCourseById(String courseId) {
        logger.info("CourseServiceImpl.class: deleteCourseById(): start");
        courseRepository.deleteById(courseId);
        return courseId;
    }
}
