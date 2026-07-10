package com.viraj.identityhub_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viraj.identityhub_api.entity.AppGroup;
import com.viraj.identityhub_api.entity.Entitlement;
import com.viraj.identityhub_api.repository.AppGroupRepository;
import com.viraj.identityhub_api.repository.EntitlementRepository;

@Service
public class AppGroupService {

    @Autowired
    private AppGroupRepository repository;

    @Autowired
    private EntitlementRepository entitlementRepository;

    /*-------------------------------------------------------
     * Group CRUD
     -------------------------------------------------------*/

    public List<AppGroup> getAllGroups() {
        return repository.findAll();
    }

    public AppGroup createGroup(AppGroup group) {
        return repository.save(group);
    }

    public AppGroup getGroupById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }

    public AppGroup updateGroup(Long id, AppGroup group) {

        AppGroup existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        existing.setGroupName(group.getGroupName());
        existing.setDescription(group.getDescription());

        return repository.save(existing);
    }

    public void deleteGroup(Long id) {
        repository.deleteById(id);
    }

    /*-------------------------------------------------------
     * Entitlement Management
     -------------------------------------------------------*/

    public AppGroup assignEntitlement(Long groupId, Long entitlementId) {

        AppGroup group = repository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Entitlement entitlement = entitlementRepository.findById(entitlementId)
                .orElseThrow(() -> new RuntimeException("Entitlement not found"));

        group.getEntitlements().add(entitlement);

        return repository.save(group);
    }

    public AppGroup removeEntitlement(Long groupId, Long entitlementId) {

        AppGroup group = repository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Entitlement entitlement = entitlementRepository.findById(entitlementId)
                .orElseThrow(() -> new RuntimeException("Entitlement not found"));

        group.getEntitlements().remove(entitlement);

        return repository.save(group);
    }

}