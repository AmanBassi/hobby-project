package com.qa.hobbyproject;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.hobbyproject.controller.VehicleController;
import com.qa.hobbyproject.service.VehicleService;

@SpringBootTest
class HobbyProjectApplicationTests {
	
	@Autowired
	private VehicleController controller;

	@Autowired
	private VehicleService service;

	@Test
	void contextLoads() {
		assertNotNull(controller);
		assertNotNull(service);
	}

}
