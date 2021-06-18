package com.qa.hobbyproject.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {

	static Task task;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		task = new Task(1L, "MOT", LocalDate.of(2021, 7, 1), new Vehicle());
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testHashCode() {
		Task task1 = new Task(1L, "MOT", LocalDate.of(2021, 7, 1));
		Task task2 = new Task(1L, "MOT", LocalDate.of(2021, 7, 1));

		assertTrue(task1.hashCode() == task2.hashCode());
	}

	@Test
	void testVehicleTask() {
		Task vt = new Task();

		assertThat(vt).isInstanceOf(Task.class);
	}

	@Test
	void testVehicleTaskStringLocalDate() {
		Task vt = new Task("MOT", LocalDate.of(2021, 7, 1));

		assertThat(vt).isInstanceOf(Task.class);
	}

	@Test
	void testVehicleTaskLongStringLocalDate() {
		Task vt = new Task(1L, "MOT", LocalDate.of(2021, 7, 1));

		assertThat(vt).isInstanceOf(Task.class);
	}

	@Test
	void testVehicleTaskStringLocalDateVehicle() {
		Task vt = new Task("MOT", LocalDate.of(2021, 7, 1), new Vehicle());

		assertThat(vt).isInstanceOf(Task.class);
	}

	@Test
	void testVehicleTaskLongStringLocalDateVehicle() {
		Task vt = new Task(1L, "MOT", LocalDate.of(2021, 7, 1), new Vehicle());

		assertThat(vt).isInstanceOf(Task.class);
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
	void testGetVehicle() {
		assertThat(task.getVehicle()).isEqualTo(new Vehicle());
	}

	@Test
	void testSetVehicle() {
		Vehicle v = new Vehicle(1L, "FE65 PKK", "VW", "Golf", "Black", 220);
		task.setVehicle(v);
		assertThat(task.getVehicle()).isEqualTo(v);
	}

	@Test
	void testToString() {
		String output = "Task [id=1, name=MOT, dueDate=2021-07-01]";
		assertThat(task.toString()).isEqualTo(output);
	}

	@Test
	void testEqualsObject() {
		Task task1 = new Task(1L, "MOT", LocalDate.of(2021, 7, 1));
		Task task2 = new Task(1L, "MOT", LocalDate.of(2021, 7, 1));

		assertTrue(task1.equals(task2) && task2.equals(task1));
	}

}
