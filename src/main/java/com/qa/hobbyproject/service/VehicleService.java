package com.qa.hobbyproject.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.hobbyproject.domain.Vehicle;
import com.qa.hobbyproject.dto.VehicleDTO;
import com.qa.hobbyproject.repository.VehicleRepository;

@Service
public class VehicleService {
	private VehicleRepository repository;
	private ModelMapper mapper;

	public VehicleService(VehicleRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public VehicleDTO addVehicle(Vehicle vehicle) {
		Vehicle saved = this.repository.save(vehicle);
		return this.mapper.map(saved, VehicleDTO.class);
	}
}
