package com.viraj.identityhub_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viraj.identityhub_api.dto.HealthDTO;
import com.viraj.identityhub_api.service.HealthService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class HealthController {

    @Autowired
    private HealthService service;

    @Operation(summary = "Application Health Check")
    @GetMapping("/health")
    public HealthDTO health() {

        return service.getHealth();
    }

}