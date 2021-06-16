package com.qa.hobbyproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.hobbyproject.domain.Vehicle;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:sql-schema.sql",
		"classpath:sql-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
class VehicleControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddVehicle() throws Exception {
		Vehicle vehicle = new Vehicle("FE65 PKK", "VW", "Golf", "Black", 220);
		Vehicle createdVehicle = new Vehicle(2L, "FE65 PKK", "VW", "Golf", "Black", 220);

		String vehicleAsJSON = this.mapper.writeValueAsString(vehicle);
		String createdVehicleAsJSON = this.mapper.writeValueAsString(createdVehicle);

		RequestBuilder mockRequest = post("/vehicle/create").content(vehicleAsJSON).contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(createdVehicleAsJSON);

		mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetAll() throws Exception {
		Vehicle vehicle = new Vehicle(1L, "PB08 BSB", "Porsche", "Macan", "Blue", 258);
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(vehicle);
		String vehiclesAsJSON = this.mapper.writeValueAsString(vehicles);

		RequestBuilder mockRequest = get("/vehicle/getAll");

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(vehiclesAsJSON);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testUpdateVehicle() throws Exception {
		Vehicle vehicle = new Vehicle(1L, "BP14 NRE", "Suzuki", "Swift", "Grey", 90);
		String vehicleAsJSON = this.mapper.writeValueAsString(vehicle);

		RequestBuilder mockRequest = put("/vehicle/update/1").contentType(MediaType.APPLICATION_JSON).content(vehicleAsJSON);

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(vehicleAsJSON);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testDeleteVehicle() throws Exception {
		RequestBuilder mockRequest = delete("/vehicle/delete/1");

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().string("true");
		
		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

}
