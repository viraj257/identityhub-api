package com.viraj.identityhub_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.viraj.identityhub_api.entity.AppGroup;
import com.viraj.identityhub_api.service.AppGroupService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/groups")
@Tag(name = "Group APIs", description = "Application Group Management")
public class AppGroupController {

    @Autowired
    private AppGroupService service;

    /*-------------------------------------------------------
     * Group CRUD
     -------------------------------------------------------*/

    @Operation(summary = "Get all groups")
    @GetMapping
    public List<AppGroup> getAllGroups() {
        return service.getAllGroups();
    }

    @Operation(summary = "Create group")
    @PostMapping
    public AppGroup createGroup(@RequestBody AppGroup group) {
        return service.createGroup(group);
    }

    @Operation(summary = "Get group by ID")
    @GetMapping("/{id}")
    public AppGroup getGroupById(@PathVariable Long id) {
        return service.getGroupById(id);
    }

    @Operation(summary = "Update group")
    @PutMapping("/{id}")
    public AppGroup updateGroup(@PathVariable Long id,
                                @RequestBody AppGroup group) {

        return service.updateGroup(id, group);
    }

    @Operation(summary = "Delete group")
    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable Long id) {

        service.deleteGroup(id);

        return "Group deleted successfully.";
    }

    /*-------------------------------------------------------
     * Entitlement Management
     -------------------------------------------------------*/

    @Operation(summary = "Assign entitlement to group")
    @PostMapping("/{groupId}/entitlements/{entitlementId}")
    public AppGroup assignEntitlement(
            @PathVariable Long groupId,
            @PathVariable Long entitlementId) {

        return service.assignEntitlement(groupId, entitlementId);
    }

    @Operation(summary = "Remove entitlement from group")
    @DeleteMapping("/{groupId}/entitlements/{entitlementId}")
    public AppGroup removeEntitlement(
            @PathVariable Long groupId,
            @PathVariable Long entitlementId) {

        return service.removeEntitlement(groupId, entitlementId);
    }

}