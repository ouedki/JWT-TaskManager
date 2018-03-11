package com.taskManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskManager.models.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long>{
	AppRole findByRole(String role);
}
