
package com.alten.repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.alten.model.Vehicle;
import com.alten.model.OnlineStatus;

@Repository
public interface VehicleRepository extends PagingAndSortingRepository<Vehicle, Long> {
	
	public Optional<Vehicle> findByRegisterationNumber(String registerationNumber);

	public Page<Vehicle> findByCustomerID(Long customerID, Pageable pageable);

	public List<Vehicle> deleteByCustomerID(Long customerID);
	
	public Page<Vehicle> findVehicleByStatus(OnlineStatus status, Pageable pageable);

	public long countByStatus(OnlineStatus status);

	@Modifying
	@Query("UPDATE Vehicle d SET d.status = 'OFFLINE' WHERE d.status = 'ONLINE' and d.lastPingDate < ?1")
	public int invalidateOnlineVehicleStatus(ZonedDateTime invalidationTime);
}