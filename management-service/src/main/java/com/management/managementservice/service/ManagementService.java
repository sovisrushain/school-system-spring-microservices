package com.management.managementservice.service;

import com.management.managementservice.dto.CourseDetailsResponse;
import com.management.managementservice.dto.SaveStudentRequest;
import com.management.managementservice.dto.SearchStudentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ManagementService {
    ResponseEntity<SearchStudentResponse> getDetails(String studentId);
    ResponseEntity<List<CourseDetailsResponse>> getAllCourseDetails();
    ResponseEntity<String> saveStudent(SaveStudentRequest saveStudentRequest);
}
