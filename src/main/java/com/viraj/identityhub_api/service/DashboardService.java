package com.viraj.identityhub_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viraj.identityhub_api.dto.DashboardDTO;
import com.viraj.identityhub_api.repository.AppGroupRepository;

import com.viraj.identityhub_api.repository.EmployeeRepository;
import com.viraj.identityhub_api.repository.EntitlementRepository;
import com.viraj.identityhub_api.repository.RoleRepository;

@Service
public class DashboardService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AppGroupRepository appGroupRepository;

    @Autowired
    private EntitlementRepository entitlementRepository;

    public DashboardDTO getDashboard() {

        DashboardDTO dashboard = new DashboardDTO();

        dashboard.setTotalEmployees(employeeRepository.count());

        dashboard.setActiveEmployees(
                employeeRepository.findByStatusIgnoreCase("ACTIVE").size());

        dashboard.setDisabledEmployees(
                employeeRepository.findByStatusIgnoreCase("DISABLED").size());

        dashboard.setLockedEmployees(
                employeeRepository.findByStatusIgnoreCase("LOCKED").size());

        dashboard.setTotalRoles(roleRepository.count());

        dashboard.setTotalGroups(appGroupRepository.count());

        dashboard.setTotalEntitlements(entitlementRepository.count());

        return dashboard;
    }
}