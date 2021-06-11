package com.qa.hobbyproject.service;

import org.springframework.stereotype.Service;

import com.qa.hobbyproject.domain.Vehicle;
import com.qa.hobbyproject.repository.VehicleRepository;

@Service
public class VehicleService {
	private VehicleRepository repository;
	
	public VehicleService(VehicleRepository repository) {
		this.repository = repository;
	}
	
	public Vehicle addVehicle(Vehicle vehicle) {
		return this.repository.save(vehicle);
	}
}
