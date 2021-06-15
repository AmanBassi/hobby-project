package com.qa.hobbyproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
@ActiveProfiles("test")
@Sql(scripts = { "classpath:schema-test.sql", "classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
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
		Vehicle createdVehicle = new Vehicle(1L, "FE65 PKK", "VW", "Golf", "Black", 220);

		String vehicleAsJSON = this.mapper.writeValueAsString(vehicle);
		String createdVehicleAsJSON = this.mapper.writeValueAsString(createdVehicle);

		RequestBuilder mockRequest = post("/create").content(vehicleAsJSON).contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(createdVehicleAsJSON);

		mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

}
