package com.course.courseservice.service;

import com.course.courseservice.dto.CourseDTO;
import com.course.courseservice.model.CourseDAO;
import com.course.courseservice.repository.CourseRepository;;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public String addNewCourse(CourseDTO courseDTO) {
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
        Optional<CourseDAO> course = courseRepository.findById(courseId);
        return CourseDTO.builder()
                .courseId(course.get().getCourseId())
                .courseName(course.get().getCourseName())
                .courseDuration(course.get().getCourseDuration())
                .build();
    }

    @Override
    public List<CourseDTO> getAllCourses() {
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
        courseRepository.deleteById(courseId);
        return courseId;
    }
}
