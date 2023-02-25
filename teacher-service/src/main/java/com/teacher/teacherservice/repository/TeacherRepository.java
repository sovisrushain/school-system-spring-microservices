package com.teacher.teacherservice.repository;

import com.teacher.teacherservice.model.TeacherDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherDAO, String> {
}
