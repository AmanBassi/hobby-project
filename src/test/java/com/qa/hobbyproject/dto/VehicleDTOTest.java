package com.qa.hobbyproject.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VehicleDTOTest {

	static VehicleDTO vehicle;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		vehicle = new VehicleDTO(1L, "FE65 PKK", "VW", "Golf", "Black", 220);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testHashCode() {
		VehicleDTO vechicle1 = new VehicleDTO(1L, "FE65 PKK", "VW", "Golf", "Black", 220);
		VehicleDTO vechicle2 = new VehicleDTO(1L, "FE65 PKK", "VW", "Golf", "Black", 220);

		assertTrue(vechicle1.hashCode() == vechicle2.hashCode());
	}

	@Test
	void testVehicleDTO() {
		VehicleDTO v = new VehicleDTO();

		assertThat(v).isInstanceOf(VehicleDTO.class);
	}

	@Test
	void testVehicleDTOStringStringStringStringInt() {
		VehicleDTO v = new VehicleDTO("FE65 PKK", "VW", "Golf", "Black", 220);

		assertThat(v).isInstanceOf(VehicleDTO.class);
	}

	@Test
	void testVehicleDTOLongStringStringStringStringInt() {
		VehicleDTO v = new VehicleDTO(1L, "FE65 PKK", "VW", "Golf", "Black", 220);

		assertThat(v).isInstanceOf(VehicleDTO.class);
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
	void testToString() {
		String output = "VehicleDTO [id=1, registration=FE65 PKK, make=VW, model=Golf, colour=Black, horsePower=220]";
		assertThat(vehicle.toString()).isEqualTo(output);
	}

	@Test
	void testEqualsObject() {
		VehicleDTO vechicle1 = new VehicleDTO(1L, "FE65 PKK", "VW", "Golf", "Black", 220);
		VehicleDTO vechicle2 = new VehicleDTO(1L, "FE65 PKK", "VW", "Golf", "Black", 220);

		assertTrue(vechicle1.equals(vechicle2) && vechicle2.equals(vechicle1));
	}

}
