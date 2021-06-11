package com.qa.hobbyproject.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.hobbyproject.domain.Vehicle;
import com.qa.hobbyproject.service.VehicleService;

@RestController
public class VehicleController {
	private VehicleService service;
	
	public VehicleController(VehicleService service) {
		this.service = service;
	}
	
	// Create
	@PostMapping("/create")
	public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
		return this.service.addVehicle(vehicle);
	}
}
