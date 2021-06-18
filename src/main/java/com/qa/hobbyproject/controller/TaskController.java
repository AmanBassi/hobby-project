package com.qa.hobbyproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.hobbyproject.domain.Task;
import com.qa.hobbyproject.dto.TaskDTO;
import com.qa.hobbyproject.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService service;

	public TaskController(TaskService service) {
		this.service = service;
	}

	// Create
	@PostMapping("/create")
	public TaskDTO addTask(@RequestBody Task task) {
		return this.service.addTask(task);
	}

	// Read
	@GetMapping("/getAllByVehicle/{id}")
	public List<TaskDTO> getAll(@PathVariable Long id) {
		return this.service.getTasksByVehicleId(id);
	}

	// Update
	@PutMapping("/update/{id}")
	public TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskDTO task) {
		return this.service.updateTask(id, task);
	}

	// Delete
	@DeleteMapping("/delete/{id}")
	public boolean deleteTask(@PathVariable Long id) {
		return this.service.deleteTask(id);
	}
}
