package com.management.managementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailsResponse {
    private String courseId;
    private String courseName;
    private int courseDuration;
    private String teacherName;
}
