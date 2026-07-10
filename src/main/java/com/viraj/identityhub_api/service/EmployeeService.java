package com.viraj.identityhub_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.viraj.identityhub_api.exception.ResourceNotFoundException;
import com.viraj.identityhub_api.dto.EmployeeDTO;
import com.viraj.identityhub_api.entity.AppGroup;
import com.viraj.identityhub_api.entity.Employee;
import com.viraj.identityhub_api.entity.Role;
import com.viraj.identityhub_api.mapper.EmployeeMapper;
import com.viraj.identityhub_api.repository.AppGroupRepository;
import com.viraj.identityhub_api.repository.EmployeeRepository;
import com.viraj.identityhub_api.repository.RoleRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AppGroupRepository appGroupRepository;

    /*-------------------------------------------------------
     * Employee CRUD
     -------------------------------------------------------*/

    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee getEmployeeById(Long id) {

        return repository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    public List<EmployeeDTO> getAllEmployees() {

        return repository.findAll()
                .stream()
                .map(EmployeeMapper::toDTO)
                .toList();
    }

    public Employee updateEmployee(Long id, Employee employee) {

        Employee existing = repository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        existing.setEmployeeId(employee.getEmployeeId());
        existing.setUsername(employee.getUsername());
        existing.setFirstName(employee.getFirstName());
        existing.setLastName(employee.getLastName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());
        existing.setDesignation(employee.getDesignation());
        existing.setManager(employee.getManager());
        existing.setStatus(employee.getStatus());

        return repository.save(existing);
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    /*-------------------------------------------------------
     * Role Management
     -------------------------------------------------------*/

    public Employee assignRole(Long employeeId, Long roleId) {

        Employee employee = repository.findById(employeeId)
        		.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        employee.getRoles().add(role);

        return repository.save(employee);
    }

    public Employee removeRole(Long employeeId, Long roleId) {

        Employee employee = repository.findById(employeeId)
        		.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        employee.getRoles().remove(role);

        return repository.save(employee);
    }

    /*-------------------------------------------------------
     * Group Management
     -------------------------------------------------------*/

    public Employee assignGroup(Long employeeId, Long groupId) {

        Employee employee = repository.findById(employeeId)
        		.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        AppGroup group = appGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        employee.getGroups().add(group);

        return repository.save(employee);
    }

    public Employee removeGroup(Long employeeId, Long groupId) {

        Employee employee = repository.findById(employeeId)
        		.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        AppGroup group = appGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        employee.getGroups().remove(group);

        return repository.save(employee);
    }

    /*-------------------------------------------------------
     * Account Lifecycle
     -------------------------------------------------------*/

    public Employee enableEmployee(Long employeeId) {

        Employee employee = repository.findById(employeeId)
        		.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setStatus("ACTIVE");

        return repository.save(employee);
    }

    public Employee disableEmployee(Long employeeId) {

        Employee employee = repository.findById(employeeId)
        		.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setStatus("DISABLED");

        return repository.save(employee);
    }

    public Employee lockEmployee(Long employeeId) {

        Employee employee = repository.findById(employeeId)
        		.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setStatus("LOCKED");

        return repository.save(employee);
    }

    public Employee unlockEmployee(Long employeeId) {

        Employee employee = repository.findById(employeeId)
        		.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setStatus("ACTIVE");

        return repository.save(employee);
    }

}