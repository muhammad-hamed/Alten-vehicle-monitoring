package com.alten.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alten.dto.VehicleStatsDTO;
import com.alten.model.OnlineStatus;
import com.alten.model.PingSignal;
import com.alten.model.Vehicle;

/**
 *  Vehicle Service API
 * @author muhammad
 */
public interface VehicleService {

	/**
	 * Get all the vehicles date.
	 * @return All the vehicles.
	 */
	Collection<Vehicle> getVehicles();

	/**
	 * Get the vehicle with Paging features.
	 * @param pageable page request.
	 * @return All the Vehicle in the requested page.
	 */
	Page<Vehicle> getVehicles(Pageable pageable);

	/**
	 * Get vehicle with a specific status. 
	 * @param status the vehicle status ONLINE/OFFLINE
	 * @param pageable page request.
	 * @return Vehicles with the requested status in the request page.
	 */
	Page<Vehicle> getVhiclesByStatus(OnlineStatus status, Pageable pageable);

	/**
	 * Get the vehicle for owned by a customer
	 * @param customerID the customer identifier.
	 * @param pageable page request
	 * @return a page of vehicle owned by the requested customer.
	 */
	Page<Vehicle> getVehicleByCustomerID(Long customerID, Pageable pageable);

	/**
	 * Get the vehicles by the Database generated identifier.
	 * @param id the Database Vehicle identifier.
	 * @return the vehicle with the requested id.
	 */
	Vehicle getVehicleByID(Long id);

	/**
	 * Get the vehicle by the registration number.
	 * @param registerationNumber
	 * @return the vehicle related to the request registration number.
	 */
	Vehicle getVehicleByRegisterationNumber(String registerationNumber);

	/**
	 * Handle the vehicle status signaling
	 * @param registerationNumber the vehicle registration number.
	 * @return the Signal info.
	 */
	PingSignal ping(String registerationNumber);

	/**
	 * Store a new vehicle of update with a modified one.
	 * @param vehicle the vehicle that would be saved
	 * @return the saved vehicle
	 */
	Vehicle save(Vehicle vehicle);

	/**
	 * Get a vehicle status statistics Online/Offline
	 * @return the vehicle stats {online count, offline count and total}
	 */
	VehicleStatsDTO getVehicleStats();
	
	/** 
	 * Delete Vehicles for a Customer.
	 * @param customerID the customer identifier.
	 */
	void deleteVehicleByCustomerID(Long customerID);

	/**
	 * Delete the vehicle by the database vehicle identifier
	 * @param id the database vehicle identifier.
	 */
	void delete(Long id);

}
