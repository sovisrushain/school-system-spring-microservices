package com.management.managementservice.service;

import com.management.managementservice.dto.CourseDTO;
import com.management.managementservice.dto.Response;
import com.management.managementservice.dto.StudentDTO;
import com.management.managementservice.dto.TeacherDTO;
import com.management.managementservice.exception.ResourceNotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

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

    @Value("${error.code.student.not-present}")
    private String studentNotPresentErrorMsg;

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
                .uri(teacherServiceURL + Objects.requireNonNull(student).getTeacherId())
                .retrieve()
                .bodyToMono(TeacherDTO.class)
                .block();

        CourseDTO course = webClientBuilder.build()
                .get()
                .uri(courseServiceURL + Objects.requireNonNull(teacher).getCourseId())
                .retrieve()
                .bodyToMono(CourseDTO.class)
                .block();

        Response response = Response.builder()
                .studentName(student.getStudentName())
                .teacherName(teacher.getTeacherName())
                .courseName(Objects.requireNonNull(course).getCourseName())
                .build();
        logger.info("ManagementServiceImpl.class: getDetails(): end");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Response> fallbackResponse(Exception exception) {
        logger.warn("ManagementController.class: fallbackResponse(): start");
        logger.error(exception.getMessage());
        if (exception.getMessage().contains(studentNotPresentErrorMsg)) {
            logger.warn("There is no student available for the given studentId");
            throw new ResourceNotFoundException("There is no student available for the given studentId");
        }
        Response response = Response.builder()
                .studentName(fallbackMsg)
                .teacherName(fallbackMsg)
                .courseName(fallbackMsg)
                .build();
        logger.warn("ManagementController.class: fallbackResponse(): end");
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
