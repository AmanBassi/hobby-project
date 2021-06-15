package com.qa.hobbyproject.utils;

import org.springframework.stereotype.Service;

import com.qa.hobbyproject.domain.Vehicle;
import com.qa.hobbyproject.dto.VehicleDTO;

@Service
public class VehicleMapper implements Mapper<Vehicle, VehicleDTO> {

	@Override
	public VehicleDTO mapToDTO(Vehicle vehicle) {
		VehicleDTO dto = new VehicleDTO();
		
		dto.setId(vehicle.getId());
		dto.setRegistration(vehicle.getRegistration());
		dto.setMake(vehicle.getMake());
		dto.setModel(vehicle.getModel());
		dto.setColour(vehicle.getColour());
		dto.setHorsePower(vehicle.getHorsePower());
		
		return dto;
	}

	@Override
	public Vehicle mapFromDTO(VehicleDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
