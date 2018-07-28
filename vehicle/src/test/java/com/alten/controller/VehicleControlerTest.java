package com.alten.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.FieldSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.alten.dto.VehicleDTO;
import com.alten.mapper.VehicleMapper;
import com.alten.model.OnlineStatus;
import com.alten.model.PingSignal;
import com.alten.model.Vehicle;
import com.alten.service.impl.VehicleServiceImpl;
import com.alten.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class VehicleControlerTest {

	private MockMvc mockMvc;

	@Mock
	private VehicleServiceImpl vehicleServiceImpl;

	@InjectMocks
	private VehicleController vehicleController;

	@Autowired
	private VehicleMapper vehicleMapper;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(vehicleController).build();
		FieldSetter.setField(vehicleController, vehicleController.getClass().getDeclaredField("vehicleMapper"), vehicleMapper);

	}

	@Test
	public void testGetVehicles() throws Exception {
		Vehicle vehicle = getVehicle();

		List<Vehicle> vehicleList = Arrays.asList(vehicle);
		when(vehicleServiceImpl.getVehicles(any())).thenReturn(new PageImpl(vehicleList));

		mockMvc.perform(get("/vehicle")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$..id").value(5));
	}

	@Test
	public void testGetVehicle() throws Exception {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(5l);
		vehicle.setLastPingDate(ZonedDateTime.now());

		when(vehicleServiceImpl.getVehicleByID(any())).thenReturn(vehicle);

		mockMvc.perform(get("/vehicle/5")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$..id").value(5));
	}

	@Test
	public void testSave() throws Exception {
		VehicleDTO vehicleDTO = geVehicleDTO();
		when(vehicleServiceImpl.save(any())).thenReturn(vehicleMapper.convertToEntity(vehicleDTO));

		String jsonObj = TestUtils.asJsonString(vehicleDTO);
		System.out.println("mmm jsonObj:" + jsonObj);
		mockMvc.perform(post("/vehicle").contentType(MediaType.APPLICATION_JSON).content(jsonObj)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
	}
	
	@Test
	public void testUpdate() throws Exception {
		VehicleDTO vehicleDTO = geVehicleDTO();
		when(vehicleServiceImpl.save(any())).thenReturn(vehicleMapper.convertToEntity(vehicleDTO));

		String jsonObj = TestUtils.asJsonString(vehicleDTO);
		System.out.println("mmm jsonObj:" + jsonObj);
		mockMvc.perform(put("/vehicle").contentType(MediaType.APPLICATION_JSON).content(jsonObj)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
	}

	@Test
	public void testGetVehicleByVehicleID() throws Exception {
		Vehicle vehicle = getVehicle();

		when(vehicleServiceImpl.getVehicleByRegisterationNumber(any())).thenReturn(vehicle);

		mockMvc.perform(get("/vehicle/search/registeration-number/123456")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$..id").value(5));
	}

	@Test
	public void testDeleteVehicle() throws Exception {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(5l);
		vehicle.setLastPingDate(ZonedDateTime.now());

		when(vehicleServiceImpl.getVehicleByID(any())).thenReturn(vehicle);

		mockMvc.perform(delete("/vehicle/5")).andExpect(status().isOk());
	}
	
	@Test
	public void testGetVehiclesByCustomerID() throws Exception {
		Vehicle vehicle = getVehicle();

		List<Vehicle> vehicleList = Arrays.asList(vehicle);
		when(vehicleServiceImpl.getVehicleByCustomerID(any(), any())).thenReturn(new PageImpl(vehicleList));

		mockMvc.perform(get("/vehicle/search/customerID/1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$..id").value(5));
	}

	@Test
	public void testPing() throws Exception {
		PingSignal pingSignal = new PingSignal();
		pingSignal.setRegisterationNumber("123456");

		when(vehicleServiceImpl.ping(any())).thenReturn(pingSignal);

		mockMvc.perform(get("/vehicle/ping/123456")).andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$..registerationNumber").value("123456"));
	}

	@Test
	public void testDeleteVehiclesByCustomerID() throws Exception {
		mockMvc.perform(delete("/vehicle/search/customerID/1")).andExpect(status().isOk());
	}

	/**
	 * Helper method.
	 * 
	 * @return return a default instance of {@link Vehicle}
	 */
	private Vehicle getVehicle() {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(5l);
		vehicle.setVehicleID("12345678901234567");
		vehicle.setLastPingDate(ZonedDateTime.now());
		return vehicle;
	}
	
	
	private VehicleDTO geVehicleDTO() {
		VehicleDTO vehicleDTO = new VehicleDTO();
		vehicleDTO.setId(4l);
		vehicleDTO.setCustomerID(5l);
		vehicleDTO.setVehicleID("12345678901234567");
		vehicleDTO.setRegisterationNumber("123456");
		vehicleDTO.setStatus(OnlineStatus.OFFLINE);
		return vehicleDTO;
	}

}
