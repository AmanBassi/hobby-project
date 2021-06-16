package com.qa.hobbyproject.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.hobbyproject.repository.VehicleTaskRepository;

@Service
public class VehicleTaskService {
	private VehicleTaskRepository repository;
	private ModelMapper mapper;

	public VehicleTaskService(VehicleTaskRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

}
