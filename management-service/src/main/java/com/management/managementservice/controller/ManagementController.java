package com.management.managementservice.controller;

import com.management.managementservice.dto.Response;
import com.management.managementservice.service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/management")
@CrossOrigin
@RequiredArgsConstructor
public class ManagementController {

    private final ManagementService managementService;

    private static final Logger logger = LogManager.getLogger(ManagementController.class);

    @GetMapping("/{studentId}")
    public ResponseEntity<Response> getDetails(@PathVariable String studentId) {
        logger.info("ManagementController.class: getDetails(): start");
        return managementService.getDetails(studentId);
    }

}
