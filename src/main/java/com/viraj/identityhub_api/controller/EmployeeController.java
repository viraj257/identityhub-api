package com.viraj.identityhub_api.controller;

import java.util.List;
import org.springframework.data.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.viraj.identityhub_api.dto.EmployeeDTO;
import com.viraj.identityhub_api.entity.Employee;
import com.viraj.identityhub_api.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee APIs", description = "Operations related to Employee Management")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    /*-------------------------------------------------------
     * Employee CRUD
     -------------------------------------------------------*/

    @Operation(summary = "Get Employees with Pagination & Sorting")
    @GetMapping
    public Page<EmployeeDTO> getEmployees(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "asc") String direction) {

        return service.getEmployees(page,
                size,
                sortBy,
                direction);
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
    
    /*-------------------------------------------------------
     * Search APIs
     -------------------------------------------------------*/

    @GetMapping("/search/username")
    public List<EmployeeDTO> searchByUsername(
            @RequestParam String username) {

        return service.searchByUsername(username);
    }

    @GetMapping("/search/email")
    public List<EmployeeDTO> searchByEmail(
            @RequestParam String email) {

        return service.searchByEmail(email);
    }

    @GetMapping("/search/department")
    public List<EmployeeDTO> searchByDepartment(
            @RequestParam String department) {

        return service.searchByDepartment(department);
    }

    @GetMapping("/search/status")
    public List<EmployeeDTO> searchByStatus(
            @RequestParam String status) {

        return service.searchByStatus(status);
    }

    @GetMapping("/search/keyword")
    public List<EmployeeDTO> searchByKeyword(
            @RequestParam String keyword) {

        return service.searchByKeyword(keyword);
    }

    @GetMapping("/search")
    public List<EmployeeDTO> searchDepartmentAndStatus(
            @RequestParam String department,
            @RequestParam String status) {

        return service.searchByDepartmentAndStatus(department,
                status);
    }
    
    @Operation(summary = "Bulk Create Employees")
    @PostMapping("/bulk")
    public List<Employee> createEmployees(
            @RequestBody List<Employee> employees) {

        return service.saveEmployees(employees);
    }
    
    @Operation(summary = "Bulk Enable Employees")
    @PutMapping("/bulk/enable")
    public List<Employee> enableEmployees(@RequestBody List<Long> employeeIds) {

        return service.enableEmployees(employeeIds);
    }
    
    @Operation(summary = "Bulk Disable Employees")
    @PutMapping("/bulk/disable")
    public List<Employee> disableEmployees(@RequestBody List<Long> employeeIds) {

        return service.disableEmployees(employeeIds);
    }
    
    @Operation(summary = "Bulk Delete Employees")
    @DeleteMapping("/bulk/delete")
    public void deleteEmployees(@RequestBody List<Long> employeeIds) {

        service.deleteEmployees(employeeIds);
    }
    
    @Operation(summary = "Export Employees to CSV")
    @GetMapping("/export")
    public void exportEmployees(HttpServletResponse response)
            throws IOException {

        service.exportEmployees(response);
    }

}