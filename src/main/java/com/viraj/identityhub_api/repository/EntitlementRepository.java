package com.viraj.identityhub_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viraj.identityhub_api.entity.Entitlement;

@Repository
public interface EntitlementRepository extends JpaRepository<Entitlement, Long> {

}