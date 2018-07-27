package com.alten.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.alten.model.OnlineStatus;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VehicleDTO {

	@ApiModelProperty(notes = "The database generated vehicle ID.")
	private Long id;

	@NotNull
	@Size(min = 17,message="The VehicleID should be greater than or equal 17")
	@ApiModelProperty(notes = "The vehicle identifier.")
	private String vehicleID;

	@NotNull
	@Size(min = 6, message = "The Registeration number shoubd be at least 6 digits")
	@ApiModelProperty(notes = "The vehicle regiteration number.")
	private String registerationNumber;

	@NotNull(message="The Vehichle should be owned by a Customer")
	@ApiModelProperty(notes = "The vehicle customer.")
	private Long customerID;

	@NotNull(message = "The Vehicle should have a non empty status  (ONLINE, or OFFLINE).")
	@ApiModelProperty(notes = "The vehicle Status (ONLINE, or OFFLINE).")
	private OnlineStatus status;

}
