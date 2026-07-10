package com.viraj.identityhub_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.viraj.identityhub_api.entity.Entitlement;
import com.viraj.identityhub_api.service.EntitlementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/entitlements")
@Tag(name = "Entitlement APIs", description = "Entitlement Management")
public class EntitlementController {

    private final EntitlementService service;

    public EntitlementController(EntitlementService service) {
        this.service = service;
    }

    @Operation(summary = "Get all entitlements")
    @GetMapping
    public List<Entitlement> getAllEntitlements() {
        return service.getAllEntitlements();
    }

    @Operation(summary = "Create entitlement")
    @PostMapping
    public Entitlement createEntitlement(@RequestBody Entitlement entitlement) {
        return service.createEntitlement(entitlement);
    }
}