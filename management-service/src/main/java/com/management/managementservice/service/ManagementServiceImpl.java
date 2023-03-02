package com.management.managementservice.service;

import com.management.managementservice.dto.CourseDTO;
import com.management.managementservice.dto.Response;
import com.management.managementservice.dto.StudentDTO;
import com.management.managementservice.dto.TeacherDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ManagementServiceImpl implements ManagementService {

    private final WebClient.Builder webClientBuilder;

    @Override
    @CircuitBreaker(name = "management-api", fallbackMethod = "fallbackResponse")
    public Response getDetails(String studentId) {
        StudentDTO student = webClientBuilder.build()
                .get()
                .uri("http://student-service/api/v1/student/" + studentId)
                .retrieve()
                .bodyToMono(StudentDTO.class)
                .block();

        TeacherDTO teacher = webClientBuilder.build()
                .get()
                .uri("http://teacher-service/api/v1/teacher/" + student.getTeacherId())
                .retrieve()
                .bodyToMono(TeacherDTO.class)
                .block();

        CourseDTO course = webClientBuilder.build()
                .get()
                .uri("http://course-service/api/v1/course/" + teacher.getCourseId())
                .retrieve()
                .bodyToMono(CourseDTO.class)
                .block();

        return Response.builder()
                .studentName(student.getStudentName())
                .teacherName(teacher.getTeacherName())
                .courseName(course.getCourseName())
                .build();
    }

    public Response fallbackResponse(Exception exception) {
        return Response.builder()
                .studentName(":(")
                .teacherName(":(")
                .courseName(":(")
                .build();
    }
}
