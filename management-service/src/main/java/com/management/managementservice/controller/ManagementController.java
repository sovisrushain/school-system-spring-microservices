package com.management.managementservice.controller;

import com.management.managementservice.dto.Response;
import com.management.managementservice.service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/management")
@CrossOrigin
@RequiredArgsConstructor
public class ManagementController {

    private final ManagementService managementService;

    @GetMapping("/{studentId}")
    public Response getDetails(@PathVariable String studentId) {
        return managementService.getDetails(studentId);
    }

}
