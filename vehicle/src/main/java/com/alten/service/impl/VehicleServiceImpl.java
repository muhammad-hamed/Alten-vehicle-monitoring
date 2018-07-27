package com.alten.service.impl;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alten.dto.VehicleStatsDTO;
import com.alten.model.OnlineStatus;
import com.alten.model.PingSignal;
import com.alten.model.Vehicle;
import com.alten.repository.PingSignalRepository;
import com.alten.repository.VehicleRepository;
import com.alten.service.VehicleService;

import lombok.AllArgsConstructor;

/**
 * Vehicle manager service, which handle the logic needed for storage of the
 * Vehicles.
 * 
 * @author muhammad hamed
 *
 */
@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {

	/**
	 * Vehicle Repository, to handle the retrieval of the Vehicle from/to the Database.
	 */
	private final VehicleRepository vehicleRepository;

	/**
	 * Vehicle Signal Repository.
	 */
	private final PingSignalRepository pingSignalRepository;

	/**
	 * Get all the stored vehicles.
	 */
	@Override
	public Collection<Vehicle> getVehicles() {

		return (Collection<Vehicle>) vehicleRepository.findAll();
	}

	/**
	 * Get the vehicle using the database identifier.
	 */
	@Override
	public Vehicle getVehicleByID(Long id) {

		return vehicleRepository.findById(id).get();
	}

	/**
	 * Get the vehicle using the registration number.
	 */
	@Override
	public Vehicle getVehicleByRegisterationNumber(String registerationNumber) {

		return vehicleRepository.findByRegisterationNumber(registerationNumber).get();
	}

	/**
	 * Get the Vehicles with paging features.
	 */
	@Override
	public Page<Vehicle> getVehicles(Pageable pageable) {
		return vehicleRepository.findAll(pageable);
	}

	/**
	 * Store the vehicle into the database.
	 */
	@Transactional
	@Override
	public Vehicle save(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	/**
	 * The Vehicle owned by a specific customer.
	 */
	@Override
	public Page<Vehicle> getVehicleByCustomerID(Long customerID, Pageable pageable) {
		return vehicleRepository.findByCustomerID(customerID, pageable);
	}

	/**
	 * Delete vehicle owned by a specific customer.
	 */
	@Transactional
	@Override
	public void deleteVehicleByCustomerID(Long customerID) {
		vehicleRepository.deleteByCustomerID(customerID);
	}

	/**
	 * Delete a Vehicle by the Database generated identifier.
	 */
	@Transactional
	@Override
	public void delete(Long id) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(id);
		vehicleRepository
				.delete(vehicle.orElseThrow(() -> new RuntimeException("Vehicle with " + id + " does not exist.")));
	}

	/**
	 * Handle the vehicle signal. 
	 */
	@Transactional
	@Override
	public PingSignal ping(String registerationNumber) {
		PingSignal pingSignal = new PingSignal();

		// update the Vehicle status
		Vehicle vehicle = vehicleRepository.findByRegisterationNumber(registerationNumber).get();

		pingSignal.setRegisterationNumber(registerationNumber);

		if (vehicle != null) {
			vehicle.setStatus(OnlineStatus.ONLINE);
			vehicle.setLastPingDate(ZonedDateTime.now());
			vehicleRepository.save(vehicle);
		}
		return pingSignalRepository.save(pingSignal);
	}

	/**
	 * Overview about the percentage of the Online/Offline vehicle.
	 */
	@Override
	public VehicleStatsDTO getVehicleStats() {
		Long totalVehiles = vehicleRepository.count();
		Long onlineVhicleCount = vehicleRepository.countByStatus(OnlineStatus.ONLINE);

		VehicleStatsDTO vehicleStats = new VehicleStatsDTO();
		vehicleStats.setTotal(totalVehiles);
		vehicleStats.setOnlineCount(onlineVhicleCount);
		vehicleStats.setOfflineCount(totalVehiles - onlineVhicleCount);
		return vehicleStats;
	}

	/**
	 * Get all the vehicles by Status.
	 */
	@Override
	public Page<Vehicle> getVhiclesByStatus(OnlineStatus status, Pageable pageable) {
		return vehicleRepository.findVehicleByStatus(status, pageable);
	}

	/**
	 * Job That invalidates the vehicle status which is not had any update signal
	 * from a minute of more.
	 */
	@Scheduled(fixedDelay = 60000)
	@Transactional
	public void vehicleStatusInvalidatorJob() {
		ZonedDateTime invalidationTime = ZonedDateTime.now().minusSeconds(60);
		vehicleRepository.invalidateOnlineVehicleStatus(invalidationTime);
	}

}
