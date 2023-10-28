package com.management.managementservice.service;

import com.management.managementservice.dto.CourseDTO;
import com.management.managementservice.dto.Response;
import com.management.managementservice.dto.StudentDTO;
import com.management.managementservice.dto.TeacherDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ManagementServiceImpl implements ManagementService {

    @Value("${student.service.url}")
    private String studentServiceURL;

    @Value("${teacher.service.url}")
    private String teacherServiceURL;

    @Value("${course.service.url}")
    private String courseServiceURL;

    @Value("${fallback.msg.symbol}")
    private String fallbackMsg;

    private final WebClient.Builder webClientBuilder;

    private static final Logger logger = LogManager.getLogger(ManagementServiceImpl.class);

    @Override
    @CircuitBreaker(name = "management-api", fallbackMethod = "fallbackResponse")
    public ResponseEntity<Response> getDetails(String studentId) {
        logger.info("ManagementServiceImpl.class: getDetails(): start");
        StudentDTO student = webClientBuilder.build()
                .get()
                .uri(studentServiceURL + studentId)
                .retrieve()
                .bodyToMono(StudentDTO.class)
                .block();

        TeacherDTO teacher = webClientBuilder.build()
                .get()
                .uri(teacherServiceURL + student.getTeacherId())
                .retrieve()
                .bodyToMono(TeacherDTO.class)
                .block();

        CourseDTO course = webClientBuilder.build()
                .get()
                .uri(courseServiceURL + teacher.getCourseId())
                .retrieve()
                .bodyToMono(CourseDTO.class)
                .block();

        Response response = Response.builder()
                .studentName(student.getStudentName())
                .teacherName(teacher.getTeacherName())
                .courseName(course.getCourseName())
                .build();
        logger.info("ManagementServiceImpl.class: getDetails(): end");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Response> fallbackResponse(Exception exception) {
        logger.warn("ManagementController.class: fallbackResponse(): start");
        Response response = Response.builder()
                .studentName(fallbackMsg)
                .teacherName(fallbackMsg)
                .courseName(fallbackMsg)
                .build();
        logger.warn("ManagementController.class: fallbackResponse(): end");
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
