package com.viraj.identityhub_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viraj.identityhub_api.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByUsernameContainingIgnoreCase(String username);

    List<Employee> findByEmailContainingIgnoreCase(String email);

    List<Employee> findByDepartmentContainingIgnoreCase(String department);

    List<Employee> findByStatusIgnoreCase(String status);

    List<Employee> findByDepartmentContainingIgnoreCaseAndStatusIgnoreCase(
            String department,
            String status);

    List<Employee> findByUsernameContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String username,
            String firstName,
            String lastName);

}