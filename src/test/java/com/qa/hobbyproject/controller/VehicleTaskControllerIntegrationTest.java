package com.qa.hobbyproject.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.hobbyproject.domain.Vehicle;
import com.qa.hobbyproject.domain.VehicleTask;
import com.qa.hobbyproject.dto.VehicleTaskDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:task-schema.sql",
		"classpath:task-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
class VehicleTaskControllerIntegrationTest {

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
	void testAddVehicleTask() throws Exception {
		Vehicle vehicle = new Vehicle(1L, null, null, null, null, 0);
		VehicleTask task = new VehicleTask("MOT", LocalDate.of(2021, 7, 1), vehicle);
		VehicleTaskDTO createdTask = new VehicleTaskDTO(3L, "MOT", LocalDate.of(2021, 7, 1));

		String taskAsJSON = this.mapper.writeValueAsString(task);
		String createdtaskAsJSON = this.mapper.writeValueAsString(createdTask);

		RequestBuilder mockRequest = post("/vehicletask/create").content(taskAsJSON)
				.contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(createdtaskAsJSON);

		mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetAll() throws Exception {
		VehicleTaskDTO task1 = new VehicleTaskDTO(1L, "MOT", LocalDate.of(2021, 7, 1));
		VehicleTaskDTO task2 = new VehicleTaskDTO(2L, "Service", LocalDate.of(2021, 8, 2));
		List<VehicleTaskDTO> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);
		String tasksAsJSON = this.mapper.writeValueAsString(tasks);

		RequestBuilder mockRequest = get("/vehicletask/getAllByVehicle/1");

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(tasksAsJSON);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testUpdateVehicleTask() throws Exception {
		Vehicle vehicle = new Vehicle(1L, null, null, null, null, 0);
		VehicleTask task = new VehicleTask(1L, "Insurance", LocalDate.of(2021, 9, 3), vehicle);
		VehicleTaskDTO taskDTO = new VehicleTaskDTO(1L, "Insurance", LocalDate.of(2021, 9, 3));
		
		String taskAsJSON = this.mapper.writeValueAsString(task);
		String taskDTOAsJSON = this.mapper.writeValueAsString(taskDTO);

		RequestBuilder mockRequest = put("/vehicletask/update/1").contentType(MediaType.APPLICATION_JSON)
				.content(taskAsJSON);

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(taskDTOAsJSON);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testDeleteVehicleTask() throws Exception {
		RequestBuilder mockRequest = delete("/vehicletask/delete/1");

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().string("true");
		
		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

}
