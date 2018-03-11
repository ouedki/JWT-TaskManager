package com.taskManager.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskManager.dao.AppTaskRepository;
import com.taskManager.models.AppTask;

@RestController
public class TaskRestController {
	
	@Autowired
	private AppTaskRepository appTaskRepository;

	@RequestMapping("/tasks")
	public List<AppTask> tasks(){
		return appTaskRepository.findAll();
	}
	
	@PostMapping("/tasks")
	public AppTask saveTask(@RequestBody AppTask task){
		return appTaskRepository.save(task);
	}
}
