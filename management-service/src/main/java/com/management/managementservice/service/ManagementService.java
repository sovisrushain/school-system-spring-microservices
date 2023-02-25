package com.management.managementservice.service;

import com.management.managementservice.dto.Response;

public interface ManagementService {
    Response getDetails(String studentId);
}
