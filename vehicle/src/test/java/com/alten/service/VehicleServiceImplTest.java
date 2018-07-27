package com.alten.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.alten.model.Vehicle;
import com.alten.repository.VehicleRepository;
import com.alten.service.impl.VehicleServiceImpl;

/**
 * Test class for {@code VehicleServiceImpl}  
 * @author muhammad hamed
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceImplTest {

	@Mock
	private VehicleRepository vehicleRepository;

	@InjectMocks
	private VehicleServiceImpl vehicleService;

	@Test
	public void testGetVehicleByID() {
		Vehicle vehicle = getVehicle();
		when(vehicleRepository.findById(1l)).thenReturn(Optional.of(vehicle));

		Vehicle retrunedVehicle = vehicleService.getVehicleByID(1l);
		assertEquals(vehicle, retrunedVehicle);
	}

	@Test
	public void testSave() {
		Vehicle vehicle = getVehicle();
		when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

		Vehicle savedVehicle = vehicleService.save(vehicle);
		assertEquals(vehicle, savedVehicle);
	}

	@Test
	public void testGetVehicles() {
		Vehicle vehicle = getVehicle();
		when(vehicleRepository.findAll()).thenReturn(Arrays.asList(vehicle, vehicle));

		Collection<Vehicle> retrunedVehicles = vehicleService.getVehicles();
		assertEquals(2, retrunedVehicles.size());
	}

	
	@Test 
	public void testDeleteVehicleByCustomerID() {
		
	}
	
	
	@Test 
	public void testGetVehicleByCustomerID () {}
	
	
	
	@Test 
	public void testPing() {}
	
	
	@Test 
	public void testVehicleStatusInvalidatorJob () {}
	
	/**
	 * Helper method for Vehicle dummy Object Creation.
	 * 
	 * @return
	 */
	private Vehicle getVehicle() {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(1l);
		vehicle.setVehicleID("12345678901234567");
		vehicle.setRegisterationNumber("124356");
		return vehicle;
	}

}
