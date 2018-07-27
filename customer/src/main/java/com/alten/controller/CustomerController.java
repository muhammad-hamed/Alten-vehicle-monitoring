package com.alten.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alten.dto.CustomerDTO;
import com.alten.exception.CustomerNotFountException;
import com.alten.mapper.CustomerMapper;
import com.alten.model.Customer;
import com.alten.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/customer")
@Api(value = "Customer API")
@AllArgsConstructor
public class CustomerController {

	/**
	 * Service for customer management.
	 */
	private final CustomerService customerService;

	/**
	 * Mapper from the Entity object (Database) vs. the DTO (interface with the external world). 
	 */
	private final CustomerMapper customerMapper;

	@ApiOperation(value = "List All Customers.")
	@GetMapping
	public Page<CustomerDTO> getCustomers(@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		return customerService.getCustomers(PageRequest.of(page, size))
				.map(customer -> customerMapper.convertToDto(customer));
	}

	@ApiOperation(value = "Search a customer by id.")
	@GetMapping("/{id}")
	public CustomerDTO getCustomer(@PathVariable Long id) {
		Customer customer = customerService.getCustomerByID(id);
		if (customer != null) {
			return customerMapper.convertToDto(customer);
		}
		throw new CustomerNotFountException();
	}

	@ApiOperation(value = "Delete a customer by id with the related vehichles.")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteCustomer(@PathVariable Long id) {
		Customer customer = customerService.getCustomerByID(id);
		if (customer != null) {
			customerService.delete(customer);
		} else
			throw new CustomerNotFountException();
	}

	@ApiOperation(value = "Add a new customer.")
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody CustomerDTO customer) {
		customerService.save(customerMapper.convertToEntity(customer));
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Update customer.")
	@PutMapping
	public ResponseEntity<?> update(@Valid @RequestBody CustomerDTO customer) {
		customerService.save(customerMapper.convertToEntity(customer));
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "Search Customers by Name or part of it <b>like *name* </b>.")
	@GetMapping("/search")
	public List<CustomerDTO> searchCustomers(@RequestParam String name, @RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		return customerService.searchCustomers(name).stream()
				.map(customer -> customerMapper.convertToDto(customer)).collect(Collectors.toList());
	}

}