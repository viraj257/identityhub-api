package com.viraj.identityhub_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.viraj.identityhub_api.entity.Role;
import com.viraj.identityhub_api.service.RoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/roles")
@Tag(name = "Role APIs", description = "Role Management APIs")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @Operation(summary = "Get all roles")
    @GetMapping
    public List<Role> getAllRoles() {
        return service.getAllRoles();
    }

    @Operation(summary = "Create Role")
    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return service.createRole(role);
    }
}