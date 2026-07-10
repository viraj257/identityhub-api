package com.viraj.identityhub_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.viraj.identityhub_api.entity.Entitlement;
import com.viraj.identityhub_api.repository.EntitlementRepository;

@Service
public class EntitlementService {

    private final EntitlementRepository repository;

    public EntitlementService(EntitlementRepository repository) {
        this.repository = repository;
    }

    public List<Entitlement> getAllEntitlements() {
        return repository.findAll();
    }

    public Entitlement createEntitlement(Entitlement entitlement) {
        return repository.save(entitlement);
    }
}