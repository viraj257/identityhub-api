package com.viraj.identityhub_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.viraj.identityhub_api.entity.Role;
import com.viraj.identityhub_api.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public List<Role> getAllRoles() {
        return repository.findAll();
    }

    public Role createRole(Role role) {
        return repository.save(role);
    }
}