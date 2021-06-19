package com.qa.hobbyproject.controller;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.hobbyproject.domain.Vehicle;
import com.qa.hobbyproject.domain.Task;
import com.qa.hobbyproject.dto.TaskDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:task-schema.sql", "classpath:task-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
class TaskControllerIntegrationTest {

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
	void testAddTask() throws Exception {
		Vehicle vehicle = new Vehicle(1L, null, null, null, null, 0);
		Task task = new Task("MOT", LocalDate.of(2021, 7, 1), "Due", vehicle);
		TaskDTO createdTask = new TaskDTO(3L, "MOT", LocalDate.of(2021, 7, 1), "Due");

		String taskAsJSON = this.mapper.writeValueAsString(task);
		String createdtaskAsJSON = this.mapper.writeValueAsString(createdTask);

		RequestBuilder mockRequest = post("/task/create/1").content(taskAsJSON).contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(createdtaskAsJSON);

		mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetAll() throws Exception {
		TaskDTO task1 = new TaskDTO(1L, "MOT", LocalDate.of(2021, 7, 1), "Due");
		TaskDTO task2 = new TaskDTO(2L, "Service", LocalDate.of(2021, 8, 2), "Booked");
		List<TaskDTO> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);
		String tasksAsJSON = this.mapper.writeValueAsString(tasks);

		RequestBuilder mockRequest = get("/task/getAllByVehicle/1");

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(tasksAsJSON);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testUpdateTask() throws Exception {
		Vehicle vehicle = new Vehicle(1L, null, null, null, null, 0);
		Task task = new Task(1L, "Insurance", LocalDate.of(2021, 9, 3), "Due", vehicle);
		TaskDTO taskDTO = new TaskDTO(1L, "Insurance", LocalDate.of(2021, 9, 3), "Due");

		String taskAsJSON = this.mapper.writeValueAsString(task);
		String taskDTOAsJSON = this.mapper.writeValueAsString(taskDTO);

		RequestBuilder mockRequest = put("/task/update/1").contentType(MediaType.APPLICATION_JSON).content(taskAsJSON);

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(taskDTOAsJSON);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testDeleteTask() throws Exception {
		RequestBuilder mockRequest = delete("/task/delete/1");

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().string("true");

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

}
