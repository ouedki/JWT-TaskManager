package com.taskManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskManager.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{
	AppUser findByUsername(String username);
}
