package com.alten.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alten.dto.PingSignalDTO;
import com.alten.dto.VehicleDTO;
import com.alten.dto.VehicleStatsDTO;
import com.alten.mapper.VehicleMapper;
import com.alten.model.OnlineStatus;
import com.alten.service.VehicleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/vehicle")
@Api(value = "Vehicle API")
@AllArgsConstructor
@CrossOrigin("*")
public class VehicleController {
	/**
	 * The Customer management Service.
	 */
	private final VehicleService vehicleService;

	/**
	 * DTO to Entity mapper.
	 */
	private final VehicleMapper vehicleMapper;

	@ApiOperation(value = "List All Vehicles.")
	@GetMapping
	public Page<VehicleDTO> getVehicles(@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		return vehicleService.getVehicles(PageRequest.of(page, size))
				.map(vehicleMapper::convertToDto);
	}

	@ApiOperation(value = "Get a vehicle by the id.")
	@GetMapping(path = "{id}")
	public VehicleDTO getVehicleByID(@PathVariable Long id) {
		return vehicleMapper.convertToDto(vehicleService.getVehicleByID(id));
	}

	@ApiOperation(value = "Delete a vehicle by the id.")
	@DeleteMapping(path = "{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteVehicleByID(@PathVariable Long id) {
		vehicleService.delete(id);
	}

	@ApiOperation(value = "Get a vehicle by the vehicle Status.")
	@GetMapping(path = "search/status/{status}")
	public Page<VehicleDTO> getVehicleByVehicleID(@PathVariable OnlineStatus status,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		return vehicleService.getVhiclesByStatus(status, PageRequest.of(page, size))
				.map(vehicleMapper::convertToDto);
	}

	@ApiOperation(value = "Get a vehicle by the registerationNumber.")
	@GetMapping(path = "search/registeration-number/{registerationNumber}")
	public VehicleDTO getVehicleByVehicleID(@PathVariable String registerationNumber) {
		return vehicleMapper.convertToDto(vehicleService.getVehicleByRegisterationNumber(registerationNumber));
	}

	@ApiOperation(value = "Get a vehicle by the customerID.")
	@GetMapping(path = "search/customerID/{customerID}")
	public Page<VehicleDTO> getVehiclesByCustomerID(@PathVariable Long customerID,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "100") int size) {
		return vehicleService.getVehicleByCustomerID(customerID, PageRequest.of(page, size))
				.map(vehicleMapper::convertToDto);
	}

	@ApiOperation(value = "Delete all vehicles related to the customerID.")
	@DeleteMapping(path = "search/customerID/{customerID}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteVehicleByVehicleID(@PathVariable Long customerID) {
		vehicleService.deleteVehicleByCustomerID(customerID);
	}

	@ApiOperation(value = "Add a new Vehicle.")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Valid @RequestBody VehicleDTO vehicleDto) {
		vehicleService.save(vehicleMapper.convertToEntity(vehicleDto));
	}

	@ApiOperation(value = "Update a Vehicle.")
	@PutMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void update(@Valid @RequestBody VehicleDTO vehicleDto) {
		this.save(vehicleDto);
	}

	@ApiOperation(value = "Ping Signal for a registerationNumber.")
	@GetMapping(path = "ping/{registerationNumber}")
	@ResponseStatus(HttpStatus.CREATED)
	public PingSignalDTO ping(@PathVariable String registerationNumber) {
		return vehicleMapper.convertToDto(vehicleService.ping(registerationNumber));
	}

	@ApiOperation(value = "Get the Vehicle Statistics.")
	@GetMapping(path = "stats")
	@ResponseStatus(HttpStatus.CREATED)
	public VehicleStatsDTO getVehicleStats() {
		return vehicleService.getVehicleStats();
	}

}
