package com.qa.hobbyproject.controller;

import org.springframework.web.bind.annotation.RestController;

import com.qa.hobbyproject.service.VehicleTaskService;

@RestController
public class VehicleTaskController {
	private VehicleTaskService service;

	public VehicleTaskController(VehicleTaskService service) {
		this.service = service;
	}

}
