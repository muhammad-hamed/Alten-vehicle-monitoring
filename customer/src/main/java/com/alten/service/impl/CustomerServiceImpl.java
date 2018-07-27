package com.alten.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alten.exception.CustomerNotFountException;
import com.alten.model.Customer;
import com.alten.repository.CustomerRepository;
import com.alten.service.CustomerService;
import com.alten.service.CustomerVehicleService;

import lombok.AllArgsConstructor;

/**
 * A customer management implementation base on the Spring Data, over a
 * relational Database.
 * 
 * @author muhammad hamed
 *
 */
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	/**
	 *  The customer repository management.
	 */
	private final CustomerRepository customerRepository;

	/**
	 * The Customer Vehicle service.
	 */
	private final CustomerVehicleService vehicleService;

	/**
	 * Get the customer by the customer database generated identifier.
	 */
	@Override
	public Customer getCustomerByID(Long customerID) {
		Optional<Customer> optionalCustomer = customerRepository.findById(customerID);
		return optionalCustomer.orElseThrow(CustomerNotFountException::new);
	}

	/**
	 * Add or Update customers.
	 */
	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	/**
	 * Delete Customer, and related Vehicles.
	 */
	@Override
	@Transactional
	public void delete(Customer customer) {
		customerRepository.delete(customer);
		vehicleService.deleteCustomerVehicles(customer.getId());
	}

	/**
	 * Customer inquiry using the paging feature.
	 */
	@Override
	public Page<Customer> getCustomers(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}

	/**
	 * Search the customer by the customer's name or part of it <b>*name*</b>.
	 */
	@Override
	public List<Customer> searchCustomers(String name) {
		return customerRepository.findCustomerByPartOfName(name);
	}

}
