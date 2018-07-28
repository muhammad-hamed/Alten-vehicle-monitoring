package com.alten.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableScheduling
@EnableTransactionManagement
public class ApplicationConfig {

	@Bean(name="restTemplate")
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean(name="modelMapper")
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
}
