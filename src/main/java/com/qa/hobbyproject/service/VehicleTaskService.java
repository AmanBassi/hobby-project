package com.qa.hobbyproject.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

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

	public List<VehicleTaskDTO> getVehicleTasksByVehicleId(Long id) {
		return this.repository.findAllByVehicleId(id).stream().map(task -> this.mapper.map(task, VehicleTaskDTO.class))
				.collect(Collectors.toList());
	}

	public VehicleTaskDTO updateVehicleTask(Long id, VehicleTask task) {
		VehicleTask existingTask = this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException());

		existingTask.setName(task.getName());
		existingTask.setDueDate(task.getDueDate());

		VehicleTask updatedTask = this.repository.save(existingTask);

		return this.mapper.map(updatedTask, VehicleTaskDTO.class);
	}
	
	public boolean deleteVehicleTask(Long id) {
		this.repository.deleteById(id);
		return !this.repository.existsById(id);
	}

}
