package com.teacher.teacherservice.model;

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
@Table(name = "Teacher")
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDAO {
    @Id
    @Column(name = "teacher_id", nullable = false)
    private String teacherId;
    @Column(name = "course_id", nullable = false)
    private String courseId;
    @Column(name = "teacher_name", nullable = false)
    private String teacherName;
}
