package com.alten.service.impl;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alten.exception.RemoteException;
import com.alten.service.CustomerVehicleService;

@Service
public class CustomerVehicleServiceImpl implements CustomerVehicleService {

	@Value("${vehicle.application.name}")
	private String vehicleAppName;

	@Value("${vehicle.service}")
	private String vehicleService;

	@Autowired
	private DiscoveryClient client;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void deleteCustomerVehicles(Long customerID) {
		List<ServiceInstance> list = client.getInstances(vehicleAppName);
		if (list != null && !list.isEmpty()) {
			URI vehicleServiceURI = list.get(0).getUri();
			if (vehicleServiceURI != null) {
				try {
					URL serviceURL = new URL(vehicleServiceURI.toURL(),
							vehicleService + "search/customerID/" + customerID);
					restTemplate.delete(serviceURL.toString());
				} catch (MalformedURLException e) {
					throw new RemoteException();
				}
			}
		}

	}

}
