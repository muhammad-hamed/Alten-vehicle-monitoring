package com.alten.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "VEHICLE")
@Setter
@Getter
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The database generated vehicle ID.")
	private Long id;

	@NotNull
	@Size(min = 17)
	@Column(name = "VEHICLE_ID")
	@ApiModelProperty(notes = "The vehicle identifier.")
	private String vehicleID;

	@NotNull
	@Size(min = 6)
	@Column(name = "REGISTERATION_NUMBER")
	@ApiModelProperty(notes = "The vehicle regiteration number.")
	private String registerationNumber;

	@NotNull
	@Column(name = "CUSTOMER_ID")
	@ApiModelProperty(notes = "The vehicle customer.")
	private Long customerID;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private OnlineStatus status;

	@Column(name="LAST_PING_DATE", nullable = true)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime lastPingDate = ZonedDateTime.now();

}
