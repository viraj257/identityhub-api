package com.viraj.identityhub_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viraj.identityhub_api.entity.AppGroup;

@Repository
public interface AppGroupRepository extends JpaRepository<AppGroup, Long> {
	
}