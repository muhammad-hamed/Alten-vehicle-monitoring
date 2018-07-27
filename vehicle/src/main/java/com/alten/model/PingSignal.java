package com.alten.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="PING_SIGNAL")
@Setter
@Getter
public class PingSignal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The database generated Ping ID.")
	private Long id;
	
	@Column(name="REGISTERATION_NUMBER", nullable = false)
	@ApiModelProperty(notes = "The vehicle Registeration Number.")
	private String registerationNumber;
	
	@Column(name="PING_DATE", nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@ApiModelProperty(notes = "The timestamp of the Ping ID.")
	private ZonedDateTime pingDate = ZonedDateTime.now();

 

}
