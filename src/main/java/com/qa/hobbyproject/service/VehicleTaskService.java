package com.qa.hobbyproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.hobbyproject.domain.VehicleTask;
import com.qa.hobbyproject.dto.VehicleTaskDTO;
import com.qa.hobbyproject.repository.VehicleTaskRepository;

@Service
public class VehicleTaskService {
	private VehicleTaskRepository repository;
	private ModelMapper mapper;

	public VehicleTaskService(VehicleTaskRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public VehicleTaskDTO addVehicleTask(VehicleTask task) {
		VehicleTask saved = this.repository.save(task);
		return this.mapper.map(saved, VehicleTaskDTO.class);
	}

	public List<VehicleTaskDTO> getTasksByVehicleId(Long id) {
		return this.repository.findAllByVehicleId(id).stream().map(task -> this.mapper.map(task, VehicleTaskDTO.class))
				.collect(Collectors.toList());
	}

}
