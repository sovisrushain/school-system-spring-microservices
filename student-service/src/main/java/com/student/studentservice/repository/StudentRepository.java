package com.student.studentservice.repository;

import com.student.studentservice.model.StudentDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentDAO, String> {
}
