package com.taskManager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskManager.models.AppTask;

public interface AppTaskRepository extends JpaRepository<AppTask, Long>{

}
