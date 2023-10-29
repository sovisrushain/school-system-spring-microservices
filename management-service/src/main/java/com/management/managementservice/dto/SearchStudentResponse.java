package com.management.managementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchStudentResponse {
    private String studentName;
    private String teacherName;
    private String courseName;
}
