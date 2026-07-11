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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
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
    
    /*-------------------------------------------------------
     * Search APIs
     -------------------------------------------------------*/

    public List<EmployeeDTO> searchByUsername(String username) {

        return repository.findByUsernameContainingIgnoreCase(username)
                .stream()
                .map(EmployeeMapper::toDTO)
                .toList();
    }

    public List<EmployeeDTO> searchByEmail(String email) {

        return repository.findByEmailContainingIgnoreCase(email)
                .stream()
                .map(EmployeeMapper::toDTO)
                .toList();
    }

    public List<EmployeeDTO> searchByDepartment(String department) {

        return repository.findByDepartmentContainingIgnoreCase(department)
                .stream()
                .map(EmployeeMapper::toDTO)
                .toList();
    }

    public List<EmployeeDTO> searchByStatus(String status) {

        return repository.findByStatusIgnoreCase(status)
                .stream()
                .map(EmployeeMapper::toDTO)
                .toList();
    }

    public List<EmployeeDTO> searchByDepartmentAndStatus(String department,
                                                         String status) {

        return repository
                .findByDepartmentContainingIgnoreCaseAndStatusIgnoreCase(department,
                        status)
                .stream()
                .map(EmployeeMapper::toDTO)
                .toList();
    }

    public List<EmployeeDTO> searchByKeyword(String keyword) {

        return repository
                .findByUsernameContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                        keyword,
                        keyword,
                        keyword)
                .stream()
                .map(EmployeeMapper::toDTO)
                .toList();
    }
    
    /*-------------------------------------------------------
     * Pagination + Sorting
     -------------------------------------------------------*/

    public Page<EmployeeDTO> getEmployees(int page,
                                          int size,
                                          String sortBy,
                                          String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable)
                .map(EmployeeMapper::toDTO);
    }
    
    /*-------------------------------------------------------
     * Bulk Provisioning
     -------------------------------------------------------*/

    public List<Employee> saveEmployees(List<Employee> employees) {
        return repository.saveAll(employees);
    }
    
    public List<Employee> enableEmployees(List<Long> employeeIds) {

        List<Employee> employees = repository.findAllById(employeeIds);

        employees.forEach(employee -> employee.setStatus("ACTIVE"));

        return repository.saveAll(employees);
    }
    
    public List<Employee> disableEmployees(List<Long> employeeIds) {

        List<Employee> employees = repository.findAllById(employeeIds);

        employees.forEach(employee -> employee.setStatus("DISABLED"));

        return repository.saveAll(employees);
    }
    
    public void deleteEmployees(List<Long> employeeIds) {

        repository.deleteAllById(employeeIds);
    }
    
    public void exportEmployees(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        response.setCharacterEncoding("UTF-8");

        response.setHeader(
                "Content-Disposition",
                "attachment; filename=employees.csv");

        List<Employee> employees = repository.findAll();

        PrintWriter writer = response.getWriter();

        CSVPrinter csvPrinter = new CSVPrinter(
                writer,
                CSVFormat.DEFAULT.withHeader(
                        "ID",
                        "Employee ID",
                        "Username",
                        "First Name",
                        "Last Name",
                        "Email",
                        "Department",
                        "Designation",
                        "Manager",
                        "Status"));

        for (Employee employee : employees) {

            csvPrinter.printRecord(
                    employee.getId(),
                    employee.getEmployeeId(),
                    employee.getUsername(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail(),
                    employee.getDepartment(),
                    employee.getDesignation(),
                    employee.getManager(),
                    employee.getStatus());
        }

        csvPrinter.flush();
    }

}