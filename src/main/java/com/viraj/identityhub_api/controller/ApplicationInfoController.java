package com.viraj.identityhub_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viraj.identityhub_api.dto.ApplicationInfoDTO;
import com.viraj.identityhub_api.service.ApplicationInfoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class ApplicationInfoController {

    @Autowired
    private ApplicationInfoService service;

    @Operation(summary = "Application Information")
    @GetMapping("/application/info")
    public ApplicationInfoDTO getApplicationInfo() {

        return service.getApplicationInfo();
    }

}