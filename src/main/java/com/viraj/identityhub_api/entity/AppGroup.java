package com.viraj.identityhub_api.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
@Table(name = "app_groups")
public class AppGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String groupName;

    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "groups")
    private Set<Employee> employees = new HashSet<>();
    
    @ManyToMany
    @JoinTable(
        name = "group_entitlements",
        joinColumns = @JoinColumn(name = "group_id"),
        inverseJoinColumns = @JoinColumn(name = "entitlement_id")
    )
    private Set<Entitlement> entitlements = new HashSet<>();

    public Set<Entitlement> getEntitlements() {
        return entitlements;
    }

    public void setEntitlements(Set<Entitlement> entitlements) {
        this.entitlements = entitlements;
    }

    public AppGroup() {
    }

    public Long getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}