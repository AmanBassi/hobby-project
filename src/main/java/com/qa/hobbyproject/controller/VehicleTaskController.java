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

import com.qa.hobbyproject.domain.VehicleTask;
import com.qa.hobbyproject.dto.VehicleTaskDTO;
import com.qa.hobbyproject.service.VehicleTaskService;

@RestController
@RequestMapping("/vehicletask")
public class VehicleTaskController {

	@Autowired
	private VehicleTaskService service;

	public VehicleTaskController(VehicleTaskService service) {
		this.service = service;
	}

	// Create
	@PostMapping("/create")
	public VehicleTaskDTO addVehicleTask(@RequestBody VehicleTask task) {
		return this.service.addVehicleTask(task);
	}

	// Read
	@GetMapping("/getAllByVehicle/{id}")
	public List<VehicleTaskDTO> getAll(@PathVariable Long id) {
		return this.service.getVehicleTasksByVehicleId(id);
	}

	// Update
	@PutMapping("/update/{id}")
	public VehicleTaskDTO updateVehicleTask(@PathVariable Long id, @RequestBody VehicleTask task) {
		return this.service.updateVehicleTask(id, task);
	}

	// Delete
	@DeleteMapping("/delete/{id}")
	public boolean deleteVehicleTask(@PathVariable Long id) {
		return this.service.deleteVehicleTask(id);
	}
}
