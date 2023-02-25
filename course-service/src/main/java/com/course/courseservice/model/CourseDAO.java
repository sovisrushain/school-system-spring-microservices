package com.course.courseservice.model;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Course")
public class CourseDAO {
    @Id
    @Column(name = "course_id", nullable = false)
    private String courseId;
    @Column(name = "course_name", nullable = false)
    private String courseName;
    @Column(name = "course_duration", nullable = false)
    private int courseDuration;
}
