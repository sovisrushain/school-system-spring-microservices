package com.management.managementservice.controller;

import com.management.managementservice.dto.CourseDetailsResponse;
import com.management.managementservice.dto.SaveStudentRequest;
import com.management.managementservice.dto.SearchStudentResponse;
import com.management.managementservice.service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/management")
@CrossOrigin
@RequiredArgsConstructor
public class ManagementController {

    private final ManagementService managementService;

    private static final Logger logger = LogManager.getLogger(ManagementController.class);

    @GetMapping("/{studentId}")
    public ResponseEntity<SearchStudentResponse> getDetails(@PathVariable String studentId) {
        logger.info("ManagementController.class: getDetails(): start");
        return managementService.getDetails(studentId);
    }

    @GetMapping("/course-details")
    public ResponseEntity<List<CourseDetailsResponse>> getAllCourseDetails() {
        logger.info("ManagementController.class: getAllCourseDetails(): start");
        return managementService.getAllCourseDetails();
    }

    @PostMapping("/save-student")
    public ResponseEntity<String> saveStudent(@RequestBody SaveStudentRequest saveStudentRequest) {
        logger.info("ManagementController.class: saveStudent(): start");
        return managementService.saveStudent(saveStudentRequest);
    }

}
