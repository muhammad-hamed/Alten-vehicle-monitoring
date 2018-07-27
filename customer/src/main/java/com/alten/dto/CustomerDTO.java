package com.alten.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDTO {

	@ApiModelProperty(notes = "The database generated customer ID.")
	private Long id;

	@NotEmpty
	@Size(min = 3, max = 150)
	@ApiModelProperty(notes = "The Customer name.")
	private String name;

	@ApiModelProperty(notes = "The Customer Address.")
	private AddressDTO address;

}
