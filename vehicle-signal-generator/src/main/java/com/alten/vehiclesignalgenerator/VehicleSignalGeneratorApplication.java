package com.alten.vehiclesignalgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VehicleSignalGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleSignalGeneratorApplication.class, args);
	}
}
