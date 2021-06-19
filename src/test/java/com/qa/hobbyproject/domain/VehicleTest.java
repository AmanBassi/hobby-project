package com.qa.hobbyproject.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VehicleTest {

	static Vehicle vehicle;

	static Task task;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(1L, "MOT", LocalDate.of(2021, 7, 1), "Due"));
		vehicle = new Vehicle(1L, "FE65 PKK", "VW", "Golf", "Black", 220, tasks);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testHashCode() {
		Vehicle vechicle1 = new Vehicle(1L, "FE65 PKK", "VW", "Golf", "Black", 220);
		Vehicle vechicle2 = new Vehicle(1L, "FE65 PKK", "VW", "Golf", "Black", 220);

		assertEquals(vechicle1.hashCode(), vechicle2.hashCode());
	}

	@Test
	void testVehicle() {
		Vehicle v = new Vehicle();

		assertThat(v).isInstanceOf(Vehicle.class);
	}

	@Test
	void testVehicleStringStringStringStringInt() {
		Vehicle v = new Vehicle("FE65 PKK", "VW", "Golf", "Black", 220);

		assertThat(v).isInstanceOf(Vehicle.class);
	}

	@Test
	void testVehicleLongStringStringStringStringInt() {
		Vehicle v = new Vehicle(1L, "FE65 PKK", "VW", "Golf", "Black", 220);

		assertThat(v).isInstanceOf(Vehicle.class);
	}

	@Test
	void testVehicleLongStringStringStringStringIntListOfVehicleTask() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task());
		Vehicle v = new Vehicle(1L, "FE65 PKK", "VW", "Golf", "Black", 220, tasks);

		assertThat(v).isInstanceOf(Vehicle.class);
	}

	@Test
	void testGetId() {
		assertThat(vehicle.getId()).isEqualTo(1L);
	}

	@Test
	void testGetRegistration() {
		assertThat(vehicle.getRegistration()).isEqualTo("FE65 PKK");
	}

	@Test
	void testGetMake() {
		assertThat(vehicle.getMake()).isEqualTo("VW");
	}

	@Test
	void testGetModel() {
		assertThat(vehicle.getModel()).isEqualTo("Golf");
	}

	@Test
	void testGetColour() {
		assertThat(vehicle.getColour()).isEqualTo("Black");
	}

	@Test
	void testGetHorsePower() {
		assertThat(vehicle.getHorsePower()).isEqualTo(220);
	}

	@Test
	void testGetTasks() {
		List<Task> tasks1 = new ArrayList<>();
		tasks1.add(new Task(1L, "MOT", LocalDate.of(2021, 7, 1), "Due"));
		assertThat(vehicle.getTasks()).isEqualTo(tasks1);
	}

	@Test
	void testSetId() {
		vehicle.setId(2L);
		assertThat(vehicle.getId()).isEqualTo(2L);
	}

	@Test
	void testSetRegistration() {
		vehicle.setRegistration("BP14 NRE");
		assertThat(vehicle.getRegistration()).isEqualTo("BP14 NRE");
	}

	@Test
	void testSetMake() {
		vehicle.setMake("Suzuki");
		assertThat(vehicle.getMake()).isEqualTo("Suzuki");
	}

	@Test
	void testSetModel() {
		vehicle.setModel("Swift");
		assertThat(vehicle.getModel()).isEqualTo("Swift");
	}

	@Test
	void testSetColour() {
		vehicle.setColour("Grey");
		assertThat(vehicle.getColour()).isEqualTo("Grey");
	}

	@Test
	void testSetHorsePower() {
		vehicle.setHorsePower(90);
		assertThat(vehicle.getHorsePower()).isEqualTo(90);
	}

	@Test
	void testSetTasks() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(1L, "Service", LocalDate.of(2021, 8, 2), "Due"));
		vehicle.setTasks(tasks);
		assertThat(vehicle.getTasks()).isEqualTo(tasks);
	}

	@Test
	void testToString() {
		String output = "Vehicle [id=1, registration=FE65 PKK, make=VW, model=Golf, colour=Black, horsePower=220, tasks=[Task [id=1, name=MOT, dueDate=2021-07-01, status=Due, vehicle=null]]]";
		assertThat(vehicle.toString()).hasToString(output);
	}

	@Test
	void testEqualsObject() {
		Vehicle vechicle1 = new Vehicle(1L, "FE65 PKK", "VW", "Golf", "Black", 220);
		Vehicle vechicle2 = new Vehicle(1L, "FE65 PKK", "VW", "Golf", "Black", 220);

		assertTrue(vechicle1.equals(vechicle2) && vechicle2.equals(vechicle1));
	}

}
