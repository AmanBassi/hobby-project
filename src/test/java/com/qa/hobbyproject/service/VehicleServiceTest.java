package com.qa.hobbyproject.service;

import static org.assertj.core.api.Assertions.assertThat;

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
		Mockito.when(this.mapper.map(createdVehicle,VehicleDTO.class)).thenReturn(savedVehicle);
		
		// THEN
		assertThat(this.service.addVehicle(vehicle)).isEqualTo(savedVehicle);

		Mockito.verify(this.repository, Mockito.times(1)).save(vehicle);
		Mockito.verify(this.mapper, Mockito.times(1)).map(createdVehicle,VehicleDTO.class);
	}

}
