package com.qa.hobbyproject.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.hobbyproject.domain.Vehicle;
import com.qa.hobbyproject.dto.VehicleDTO;
import com.qa.hobbyproject.repository.VehicleRepository;

@SpringBootTest
@ActiveProfiles("test")
class VehicleServiceTest {

	@Autowired
	private VehicleService service;

	@MockBean
	private VehicleRepository repository;

	@MockBean
	private ModelMapper mapper;

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
	void testAddVehicle() {
		Vehicle createdVehicle = new Vehicle(1L, "FE65 PKK", "VW", "Golf", "Black", 220);
		VehicleDTO savedVehicle = new VehicleDTO(1L, "FE65 PKK", "VW", "Golf", "Black", 220);

		// GIVEN
		Vehicle vehicle = new Vehicle("FE65 PKK", "VW", "Golf", "Black", 220);

		// WHEN
		Mockito.when(this.repository.save(vehicle)).thenReturn(createdVehicle);
		Mockito.when(this.mapper.map(createdVehicle, VehicleDTO.class)).thenReturn(savedVehicle);

		// THEN
		assertThat(this.service.addVehicle(vehicle)).isEqualTo(savedVehicle);

		Mockito.verify(this.repository, Mockito.times(1)).save(vehicle);
		Mockito.verify(this.mapper, Mockito.times(1)).map(createdVehicle, VehicleDTO.class);
	}

	@Test
	void testGetVehicles() {
		// GIVEN
		List<Vehicle> vehicles = new ArrayList<>();
		
		Vehicle vehicle1 = new Vehicle(1L, "FE65 PKK", "VW", "Golf", "Black", 220);
		Vehicle vehicle2 = new Vehicle(2L, "BP14 NRE", "Suzuki", "Swift", "Grey", 90);

		vehicles.add(vehicle1);
		vehicles.add(vehicle2);
		
		List<VehicleDTO> vehicleDTOs = new ArrayList<>();

		VehicleDTO vehicleDTO1 = new VehicleDTO(1L, "FE65 PKK", "VW", "Golf", "Black", 220);
		VehicleDTO vehicleDTO2 = new VehicleDTO(2L, "BP14 NRE", "Suzuki", "Swift", "Grey", 90);
		
		vehicleDTOs.add(vehicleDTO1);
		vehicleDTOs.add(vehicleDTO2);

		// WHEN
		Mockito.when(this.repository.findAll()).thenReturn(vehicles);
		Mockito.when(this.mapper.map(vehicle1, VehicleDTO.class)).thenReturn(vehicleDTO1);
		Mockito.when(this.mapper.map(vehicle2, VehicleDTO.class)).thenReturn(vehicleDTO2);

		// THEN
		assertThat(this.service.getVehicles()).isEqualTo(vehicleDTOs);
		
		Mockito.verify(this.repository, Mockito.times(1)).findAll();
		Mockito.verify(this.mapper, Mockito.times(1)).map(vehicle1, VehicleDTO.class);
		Mockito.verify(this.mapper, Mockito.times(1)).map(vehicle2, VehicleDTO.class);
	}

}
