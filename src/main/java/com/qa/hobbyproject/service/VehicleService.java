package com.qa.hobbyproject.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

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

	public VehicleDTO addVehicle(VehicleDTO vehicleDTO) {
		var vehicle = this.mapper.map(vehicleDTO, Vehicle.class);
		Vehicle saved = this.repository.save(vehicle);
		return this.mapper.map(saved, VehicleDTO.class);
	}

	public List<VehicleDTO> getVehicles() {
		return this.repository.findAll().stream().map(vehicle -> this.mapper.map(vehicle, VehicleDTO.class)).collect(Collectors.toList());
	}

	public VehicleDTO getVehicleById(Long id) {
		Optional<Vehicle> optionalVehicle = this.repository.findById(id);
		var foundVehicle = optionalVehicle.orElseThrow(EntityNotFoundException::new);
		return this.mapper.map(foundVehicle, VehicleDTO.class);
	}

	public VehicleDTO updateVehicle(Long id, VehicleDTO vehicle) {
		var existingVehicle = this.repository.findById(id).orElseThrow(EntityNotFoundException::new);

		existingVehicle.setRegistration(vehicle.getRegistration());
		existingVehicle.setMake(vehicle.getMake());
		existingVehicle.setModel(vehicle.getModel());
		existingVehicle.setColour(vehicle.getColour());
		existingVehicle.setHorsePower(vehicle.getHorsePower());

		var updatedVehicle = this.repository.save(existingVehicle);

		return this.mapper.map(updatedVehicle, VehicleDTO.class);
	}

	public boolean deleteVehicle(Long id) {
		this.repository.deleteById(id);
		return !this.repository.existsById(id);
	}
}
