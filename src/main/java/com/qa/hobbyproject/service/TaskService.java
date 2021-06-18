package com.qa.hobbyproject.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.hobbyproject.domain.Task;
import com.qa.hobbyproject.domain.Vehicle;
import com.qa.hobbyproject.dto.TaskDTO;
import com.qa.hobbyproject.repository.TaskRepository;

@Service
public class TaskService {
	private TaskRepository repository;
	private ModelMapper mapper;

	public TaskService(TaskRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public TaskDTO addTask(Long id, TaskDTO taskDTO) {
		var task = this.mapper.map(taskDTO, Task.class);
		task.setVehicle(new Vehicle(id, "", "", "", "", 0));
		Task saved = this.repository.save(task);
		return this.mapper.map(saved, TaskDTO.class);
	}

	public List<TaskDTO> getTasksByVehicleId(Long id) {
		return this.repository.findAllByVehicleId(id).stream().map(task -> this.mapper.map(task, TaskDTO.class)).collect(Collectors.toList());
	}

	public TaskDTO updateTask(Long id, TaskDTO task) {
		var existingTask = this.repository.findById(id).orElseThrow(EntityNotFoundException::new);

		existingTask.setName(task.getName());
		existingTask.setDueDate(task.getDueDate());

		var updatedTask = this.repository.save(existingTask);

		return this.mapper.map(updatedTask, TaskDTO.class);
	}

	public boolean deleteTask(Long id) {
		this.repository.deleteById(id);
		return !this.repository.existsById(id);
	}

}
