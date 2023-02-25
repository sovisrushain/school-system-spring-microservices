package com.course.courseservice.repository;

import com.course.courseservice.model.CourseDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseDAO, String> {
}
