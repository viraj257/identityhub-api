package com.viraj.identityhub_api.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "entitlements")
public class Entitlement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String entitlementName;

    private String type;

    private String application;

    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "entitlements")
    private Set<AppGroup> groups = new HashSet<>();

    public Entitlement() {
    }

    public Long getId() {
        return id;
    }

    public String getEntitlementName() {
        return entitlementName;
    }

    public void setEntitlementName(String entitlementName) {
        this.entitlementName = entitlementName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AppGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<AppGroup> groups) {
        this.groups = groups;
    }
}