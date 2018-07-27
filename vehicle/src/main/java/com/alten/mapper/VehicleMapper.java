package com.alten.mapper;


import com.alten.dto.PingSignalDTO;
import com.alten.dto.VehicleDTO;
import com.alten.model.PingSignal;
import com.alten.model.Vehicle;


public interface VehicleMapper {
	
	Vehicle convertToEntity(VehicleDTO vehicleDTO);

	VehicleDTO convertToDto(Vehicle vehicle);
	
	PingSignalDTO convertToDto(PingSignal pingSignal);

}
