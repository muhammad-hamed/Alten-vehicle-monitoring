package com.alten.dto;

import java.time.ZonedDateTime;

import org.springframework.format.annotation.DateTimeFormat;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PingSignalDTO {

	@ApiModelProperty(notes = "The database generated Ping ID.")
	private Long id;

	@ApiModelProperty(notes = "The vehicle registerationNumber.")
	private String registerationNumber;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@ApiModelProperty(notes = "The timestamp of the Ping ID.")
	private ZonedDateTime pingDate = ZonedDateTime.now();

}
