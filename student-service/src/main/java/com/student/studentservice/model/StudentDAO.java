package com.student.studentservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "Student")
@NoArgsConstructor
@AllArgsConstructor
public class StudentDAO {
    @Id
    @Column(name = "student_id", nullable = false)
    private String studentId;
    @Column(name = "teacher_id", nullable = false)
    private String teacherId;
    @Column(name = "student_name", nullable = false)
    private String studentName;
}
