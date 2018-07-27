package com.alten.vehiclesignalgenerator.job;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alten.vehiclesignalgenerator.dto.Page;
import com.alten.vehiclesignalgenerator.dto.VehicleDTO;


@Component
public class StatusGenerationJob {

	@Value("${vehicle.service.url}")
	private String vehicleServiceURL;

	@Value("${vehicle.ping.url}")
	private String vehiclePingURL;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Vehicle send Status per minute
	 */
	@Scheduled(fixedDelay=60000)
	private void generateSignal() {
		System.out.println("The Job started");
		VehicleDTO[] vehicles = restTemplate.getForObject(vehicleServiceURL, Page.class).getContent();
		
		Random random = new Random();
 		Predicate<VehicleDTO> toBeOnline = v-> random.nextBoolean();
		
		Stream.of(vehicles).filter(toBeOnline).forEach(v->sendPingSignal(v.getRegisterationNumber()));;
		
	}
	
	
	private void sendPingSignal(String vehicleRegistrationNumber) {
		restTemplate.getForObject(vehiclePingURL + vehicleRegistrationNumber, Page.class);
		System.out.println("The vehicle " + vehicleRegistrationNumber + " should be online");
	}

}
