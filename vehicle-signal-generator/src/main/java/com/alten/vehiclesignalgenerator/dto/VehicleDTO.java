package com.alten.vehiclesignalgenerator.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class VehicleDTO {
	private String registerationNumber;
}