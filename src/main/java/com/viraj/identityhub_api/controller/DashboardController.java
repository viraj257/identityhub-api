package com.viraj.identityhub_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viraj.identityhub_api.dto.DashboardDTO;
import com.viraj.identityhub_api.service.DashboardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/dashboard")
@Tag(name = "Dashboard APIs")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Operation(summary = "Get Dashboard Statistics")
    @GetMapping
    public DashboardDTO getDashboard() {

        return dashboardService.getDashboard();
    }

}