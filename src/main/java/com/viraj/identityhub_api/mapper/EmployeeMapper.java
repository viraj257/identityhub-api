package com.viraj.identityhub_api.mapper;

import java.util.stream.Collectors;

import com.viraj.identityhub_api.dto.EmployeeDTO;
import com.viraj.identityhub_api.entity.Employee;

import com.viraj.identityhub_api.entity.AppGroup;
import java.util.Set;

public class EmployeeMapper {

    public static EmployeeDTO toDTO(Employee employee) {

        EmployeeDTO dto = new EmployeeDTO();

        dto.setId(employee.getId());
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setUsername(employee.getUsername());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(employee.getDepartment());
        dto.setDesignation(employee.getDesignation());
        dto.setManager(employee.getManager());
        dto.setStatus(employee.getStatus());

        dto.setRoles(
                employee.getRoles()
                        .stream()
                        .map(role -> role.getRoleName())
                        .collect(Collectors.toSet()));
        
        dto.setGroups(
        	    employee.getGroups()
        	            .stream()
        	            .map(AppGroup::getGroupName)
        	            .collect(Collectors.toSet()));
        
        

        return dto;
    }


}