package com.qa.hobbyproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.hobbyproject.domain.Vehicle;
import com.qa.hobbyproject.dto.VehicleDTO;
import com.qa.hobbyproject.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	private VehicleService service;

	public VehicleController(VehicleService service) {
		this.service = service;
	}

	// Create
	@PostMapping("/create")
	public VehicleDTO addVehicle(@RequestBody Vehicle vehicle) {
		return this.service.addVehicle(vehicle);
	}

	// Read
	@GetMapping("/getAll")
	public List<VehicleDTO> getAll() {
		return this.service.getVehicles();
	}
	
	// Update
	@PutMapping("/update/{id}")
	public VehicleDTO updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
		return this.service.updateVehicle(id, vehicle);
	}
	
	// Delete
	@DeleteMapping("/delete/{id}")
	public boolean deleteVehicle(@PathVariable Long id) {
		return this.service.deleteVehicle(id);
	}
}
