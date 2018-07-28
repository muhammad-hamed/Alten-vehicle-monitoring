package com.alten.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alten.dto.PingSignalDTO;
import com.alten.dto.VehicleDTO;
import com.alten.model.PingSignal;
import com.alten.model.Vehicle;

@Component
public class VehicleMapperImpl implements VehicleMapper {

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Mapper functions to convert from Vehicle Entity to VehicleDTO.
	 * 
	 * @param vehicle
	 *            {@link Vehicle}
	 * @return {@link VehicleDTO}
	 */
	@Override
	public VehicleDTO convertToDto(Vehicle vehicle) {
		return modelMapper.map(vehicle, VehicleDTO.class);
	}

	/**
	 * Mapper to convert from DTO to Entity.
	 * 
	 * @param vehicleDto
	 *            {@ VehicleDTO}
	 * @return {@link Vehicle}
	 */
	@Override
	public Vehicle convertToEntity(VehicleDTO vehicleDto) {
		return modelMapper.map(vehicleDto, Vehicle.class);
	}

	/**
	 * Mapper functions to convert from {@link PingSignal} Entity to
	 * {@link PingSignalDTO}.
	 * 
	 * @param pingSignal
	 *            {@link PingSignal}
	 * @return {@link PingSignalDTO}
	 */
	@Override
	public PingSignalDTO convertToDto(PingSignal pingSignal) {
		return modelMapper.map(pingSignal, PingSignalDTO.class);
	}

}
