package com.alten.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter
public class Address {

	@NotNull
	@Size(max = 150)
	private String addressLine;

	@NotNull
	@Size(max = 50)
	private String city;

	@NotNull
	@Size(max = 50)
	private String state;

	@NotNull
	@Size(max = 50)
	private String country;

	@NotNull
	@Size(max = 6)
	private String postalCode;

}
