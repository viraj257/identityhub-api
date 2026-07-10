package com.viraj.identityhub_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.viraj.identityhub_api.dto.EmployeeDTO;
import com.viraj.identityhub_api.entity.Employee;
import com.viraj.identityhub_api.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee APIs", description = "Operations related to Employee Management")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    /*-------------------------------------------------------
     * Employee CRUD
     -------------------------------------------------------*/

    @Operation(summary = "Get all employees")
    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return service.getAllEmployees();
    }

    @Operation(summary = "Create Employee")
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    @Operation(summary = "Get Employee by ID")
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @Operation(summary = "Update Employee")
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                   @RequestBody Employee employee) {

        return service.updateEmployee(id, employee);
    }

    @Operation(summary = "Delete Employee")
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        service.deleteEmployee(id);

        return "Employee deleted successfully.";
    }

    /*-------------------------------------------------------
     * Role Management
     -------------------------------------------------------*/

    @Operation(summary = "Assign Role")
    @PostMapping("/{employeeId}/roles/{roleId}")
    public Employee assignRole(@PathVariable Long employeeId,
                               @PathVariable Long roleId) {

        return service.assignRole(employeeId, roleId);
    }

    @Operation(summary = "Remove Role")
    @DeleteMapping("/{employeeId}/roles/{roleId}")
    public Employee removeRole(@PathVariable Long employeeId,
                               @PathVariable Long roleId) {

        return service.removeRole(employeeId, roleId);
    }

    /*-------------------------------------------------------
     * Group Management
     -------------------------------------------------------*/

    @Operation(summary = "Assign Group")
    @PostMapping("/{employeeId}/groups/{groupId}")
    public Employee assignGroup(@PathVariable Long employeeId,
                                @PathVariable Long groupId) {

        return service.assignGroup(employeeId, groupId);
    }

    @Operation(summary = "Remove Group")
    @DeleteMapping("/{employeeId}/groups/{groupId}")
    public Employee removeGroup(@PathVariable Long employeeId,
                                @PathVariable Long groupId) {

        return service.removeGroup(employeeId, groupId);
    }

    /*-------------------------------------------------------
     * Account Lifecycle
     -------------------------------------------------------*/

    @Operation(summary = "Enable Employee")
    @PostMapping("/{employeeId}/enable")
    public Employee enableEmployee(@PathVariable Long employeeId) {

        return service.enableEmployee(employeeId);
    }

    @Operation(summary = "Disable Employee")
    @PostMapping("/{employeeId}/disable")
    public Employee disableEmployee(@PathVariable Long employeeId) {

        return service.disableEmployee(employeeId);
    }

    @Operation(summary = "Lock Employee")
    @PostMapping("/{employeeId}/lock")
    public Employee lockEmployee(@PathVariable Long employeeId) {

        return service.lockEmployee(employeeId);
    }

    @Operation(summary = "Unlock Employee")
    @PostMapping("/{employeeId}/unlock")
    public Employee unlockEmployee(@PathVariable Long employeeId) {

        return service.unlockEmployee(employeeId);
    }

}