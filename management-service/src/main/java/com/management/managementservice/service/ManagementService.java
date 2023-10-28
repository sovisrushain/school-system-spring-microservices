package com.management.managementservice.service;

import com.management.managementservice.dto.Response;
import org.springframework.http.ResponseEntity;

public interface ManagementService {
    ResponseEntity<Response> getDetails(String studentId);
}
