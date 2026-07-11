package com.viraj.identityhub_api.service;

import org.springframework.stereotype.Service;

import com.viraj.identityhub_api.dto.HealthDTO;

@Service
public class HealthService {

    public HealthDTO getHealth() {

        return new HealthDTO(
                "UP",
                "IdentityHub API",
                "1.0.0",
                "CONNECTED");
    }

}