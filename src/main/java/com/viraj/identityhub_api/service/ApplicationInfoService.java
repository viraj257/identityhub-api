package com.viraj.identityhub_api.service;

import org.springframework.stereotype.Service;

import com.viraj.identityhub_api.dto.ApplicationInfoDTO;

@Service
public class ApplicationInfoService {

    public ApplicationInfoDTO getApplicationInfo() {

        return new ApplicationInfoDTO(
                "IdentityHub API",
                "1.0.0",
                "Development",
                "Viraj Taywade",
                "Spring Boot 3.5",
                "Java 17",
                "MySQL",
                "RUNNING");
    }

}