package com.qa.hobbyproject.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.hobbyproject.domain.VehicleTask;
import com.qa.hobbyproject.dto.VehicleTaskDTO;
import com.qa.hobbyproject.service.VehicleTaskService;

@RestController
@RequestMapping("/vehicletask")
public class VehicleTaskController {
	private VehicleTaskService service;

	public VehicleTaskController(VehicleTaskService service) {
		this.service = service;
	}

	// Create
	@PostMapping("/create")
	public VehicleTaskDTO addVehicleTask(@RequestBody VehicleTask task) {
		return this.service.addVehicleTask(task);
	}
}
