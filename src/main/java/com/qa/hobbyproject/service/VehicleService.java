package com.qa.hobbyproject.service;

import org.springframework.stereotype.Service;

import com.qa.hobbyproject.domain.Vehicle;
import com.qa.hobbyproject.dto.VehicleDTO;
import com.qa.hobbyproject.repository.VehicleRepository;
import com.qa.hobbyproject.utils.VehicleMapper;

@Service
public class VehicleService {
	private VehicleRepository repository;
	private VehicleMapper mapper;

	public VehicleService(VehicleRepository repository, VehicleMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public VehicleDTO addVehicle(Vehicle vehicle) {
		Vehicle saved = this.repository.save(vehicle);
		return this.mapper.mapToDTO(saved);
	}
}
