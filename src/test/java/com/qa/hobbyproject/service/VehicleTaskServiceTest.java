package com.qa.hobbyproject.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

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
import com.qa.hobbyproject.domain.VehicleTask;
import com.qa.hobbyproject.dto.VehicleDTO;
import com.qa.hobbyproject.dto.VehicleTaskDTO;
import com.qa.hobbyproject.repository.VehicleTaskRepository;

@SpringBootTest
@ActiveProfiles("test")
class VehicleTaskServiceTest {

	@Autowired
	private VehicleTaskService service;

	@MockBean
	private VehicleTaskRepository repository;

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
	void testVehicleTaskService() {
		VehicleTaskService vts = new VehicleTaskService(repository, mapper);
		assertThat(vts).isInstanceOf(VehicleTaskService.class);
	}

	@Test
	void testAddVehicleTask() {
		VehicleTask createdTask = new VehicleTask(1L, "MOT", LocalDate.of(2021, 7, 1));
		VehicleTaskDTO savedTask = new VehicleTaskDTO(1L, "MOT", LocalDate.of(2021, 7, 1));

		// GIVEN
		VehicleTask task = new VehicleTask("MOT", LocalDate.of(2021, 7, 1));

		// WHEN
		Mockito.when(this.repository.save(task)).thenReturn(createdTask);
		Mockito.when(this.mapper.map(createdTask, VehicleTaskDTO.class)).thenReturn(savedTask);

		// THEN
		assertThat(this.service.addVehicleTask(task)).isEqualTo(savedTask);

		Mockito.verify(this.repository, Mockito.times(1)).save(task);
		Mockito.verify(this.mapper, Mockito.times(1)).map(createdTask, VehicleTaskDTO.class);
	}

	@Test
	void testGetVehicleTasksByVehicleId() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateVehicleTask() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteVehicleTask() {
		fail("Not yet implemented");
	}

}
