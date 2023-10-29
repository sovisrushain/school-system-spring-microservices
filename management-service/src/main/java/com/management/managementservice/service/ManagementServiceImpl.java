package com.management.managementservice.service;

import com.management.managementservice.dto.*;
import com.management.managementservice.exception.ResourceNotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

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
    public ResponseEntity<SearchStudentResponse> getDetails(String studentId) {
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

        SearchStudentResponse response = SearchStudentResponse.builder()
                .studentName(student.getStudentName())
                .teacherName(teacher.getTeacherName())
                .courseName(Objects.requireNonNull(course).getCourseName())
                .build();
        logger.info("ManagementServiceImpl.class: getDetails(): end");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CourseDetailsResponse>> getAllCourseDetails() {
        logger.info("ManagementServiceImpl.class: getAllCourseDetails(): start");
        List<CourseDetailsResponse> detailList = new ArrayList<>();
        List<CourseDTO> courseList = webClientBuilder.build()
                .get()
                .uri(courseServiceURL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CourseDTO>>() {
                })
                .block();

        if (courseList == null) {
            logger.error("ManagementServiceImpl.class: getAllCourseDetails():There are no courses yet");
            throw new ResourceNotFoundException("There are no courses yet");
        }

        List<TeacherDTO> teachersList = webClientBuilder.build()
                .get()
                .uri(teacherServiceURL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<TeacherDTO>>() {
                })
                .block();

        if (teachersList == null) {
            logger.error("ManagementServiceImpl.class: getAllCourseDetails():There are no courses yet");
            throw new ResourceNotFoundException("There are no courses yet");
        }

        courseList.forEach(course -> {
            CourseDetailsResponse response = new CourseDetailsResponse();
             response.setCourseId(course.getCourseId());
             response.setCourseName(course.getCourseName());
             response.setCourseDuration(course.getCourseDuration());
             teachersList.forEach(teacher -> {
                 if (teacher.getCourseId().equals(course.getCourseId())) {
                     response.setTeacherName(teacher.getTeacherName());
                 }
             });
            logger.info("ManagementServiceImpl.class: getAllCourseDetails(): start");
            detailList.add(response);
        });

        return new ResponseEntity<>(detailList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> saveStudent(SaveStudentRequest saveStudentRequest) {
        logger.warn("ManagementController.class: saveStudent(): start");
        AtomicReference<TeacherDTO> teacher = new AtomicReference<>();
        CourseDTO course = webClientBuilder.build()
                .get()
                .uri(courseServiceURL + saveStudentRequest.getCourseId())
                .retrieve()
                .bodyToMono(CourseDTO.class)
                .block();

        if (course == null) {
            logger.error("ManagementServiceImpl.class: getAllCourseDetails():There is no course associated with given courseId");
            throw new ResourceNotFoundException("There is no course associated with given courseId");
        }

        List<TeacherDTO> teachersList = webClientBuilder.build()
                .get()
                .uri(teacherServiceURL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<TeacherDTO>>() {
                })
                .block();

        if (teachersList == null || teachersList.isEmpty()) {
            logger.error("ManagementServiceImpl.class: getAllCourseDetails():There are no teachers");
            throw new ResourceNotFoundException("There are no teachers");
        }

        teachersList.forEach(t -> {
            if (t.getCourseId().equals(course.getCourseId())) {
                teacher.set(t);
            }
        });

        StudentDTO studentDTO = StudentDTO.builder()
                .studentId(saveStudentRequest.getStudentId())
                .studentName(saveStudentRequest.getStudentName())
                .teacherId(teacher.get().getTeacherId())
                .build();

        String response = webClientBuilder.build()
                .post()
                .uri(studentServiceURL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(studentDTO), StudentDTO.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        logger.warn("ManagementController.class: saveStudent(): end");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<SearchStudentResponse> fallbackResponse(Exception exception) {
        logger.warn("ManagementController.class: fallbackResponse(): start");
        logger.error(exception.getMessage());
        if (exception.getMessage().contains(studentNotPresentErrorMsg)) {
            logger.warn("There is no student available for the given studentId");
            throw new ResourceNotFoundException("There is no student available for the given studentId");
        }
        SearchStudentResponse response = SearchStudentResponse.builder()
                .studentName(fallbackMsg)
                .teacherName(fallbackMsg)
                .courseName(fallbackMsg)
                .build();
        logger.warn("ManagementController.class: fallbackResponse(): end");
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
