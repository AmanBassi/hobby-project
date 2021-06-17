package com.qa.hobbyproject.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VehicleTaskDTOTest {

	static VehicleTaskDTO task;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		task = new VehicleTaskDTO(1L, "MOT", LocalDate.of(2021, 7, 1));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testHashCode() {
		VehicleTaskDTO task1 = new VehicleTaskDTO(1L, "MOT", LocalDate.of(2021, 7, 1));
		VehicleTaskDTO task2 = new VehicleTaskDTO(1L, "MOT", LocalDate.of(2021, 7, 1));

		assertTrue(task1.hashCode() == task2.hashCode());
	}

	@Test
	void testVehicleTaskDTO() {
		VehicleTaskDTO task = new VehicleTaskDTO();

		assertThat(task).isInstanceOf(VehicleTaskDTO.class);
	}

	@Test
	void testVehicleTaskDTOLongStringLocalDate() {
		VehicleTaskDTO task = new VehicleTaskDTO(1L, "MOT", LocalDate.of(2021, 7, 1));

		assertThat(task).isInstanceOf(VehicleTaskDTO.class);
	}

	@Test
	void testGetId() {
		assertThat(task.getId()).isEqualTo(1L);
	}

	@Test
	void testGetName() {
		assertThat(task.getName()).isEqualTo("MOT");
	}

	@Test
	void testGetDueDate() {
		assertThat(task.getDueDate()).isEqualTo(LocalDate.of(2021, 7, 1));
	}

	@Test
	void testSetId() {
		task.setId(2L);
		assertThat(task.getId()).isEqualTo(2L);
	}

	@Test
	void testSetName() {
		task.setName("MOT");
		assertThat(task.getName()).isEqualTo("MOT");
	}

	@Test
	void testSetDueDate() {
		task.setDueDate(LocalDate.of(2022, 8, 2));
		assertThat(task.getDueDate()).isEqualTo(LocalDate.of(2022, 8, 2));
	}

	@Test
	void testToString() {
		String output = "VehicleTaskDTO [id=1, name=MOT, dueDate=2021-07-01]";
		assertThat(task.toString()).isEqualTo(output);
	}

	@Test
	void testEqualsObject() {
		VehicleTaskDTO task1 = new VehicleTaskDTO(1L, "MOT", LocalDate.of(2021, 7, 1));
		VehicleTaskDTO task2 = new VehicleTaskDTO(1L, "MOT", LocalDate.of(2021, 7, 1));

		assertTrue(task1.equals(task2) && task2.equals(task1));
	}

}
