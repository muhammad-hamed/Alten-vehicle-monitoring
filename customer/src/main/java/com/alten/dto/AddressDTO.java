package com.alten.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDTO {

	@NotNull
	@Size(max = 150)
	@ApiModelProperty(notes = "The Address Line.")
	private String addressLine;

	@NotNull
	@Size(max = 50)
	@ApiModelProperty(notes = "The Address City.")
	private String city;

	@NotNull
	@Size(max = 50)
	@ApiModelProperty(notes = "The Address State.")
	private String state;

	@NotNull
	@Size(max = 50)
	@ApiModelProperty(notes = "The Address Country.")
	private String country;

	@NotNull
	@Size(max = 6)
	@ApiModelProperty(notes = "The Address Postal Code.")
	private String postalCode;
}
