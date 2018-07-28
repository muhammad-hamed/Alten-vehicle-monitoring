package com.alten.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alten.model.Customer;

/**
 * Customer management, for storing, updating, and search the customer stored in
 * the customer store.
 * 
 * @author muhammad hamed
 *
 */
public interface CustomerService {

	/**
	 *  Save a customer to the database.
	 * @param customer
	 * @return the customer saved and enriched with the auto-generated identifier.
	 */
	Customer save(Customer customer);

	/**
	 * Get the customer by the customer identifier.
	 * @param customerID the Customer identifier.
	 * @return the Customer related to the request identifier.
	 */
	Customer getCustomerByID(Long customerID);

	/**
	 * Delete The customer from the customer store.
	 * @param customer the customer to be deleted
	 */
	void delete(Customer customer);

	/**
	 * Get All the customers from the customer store.
	 * @param pageable page request
	 * @return the request page of customers.
	 */
	Page<Customer> getCustomers(Pageable pageable);

	/**
	 * Search the customers by the customer's name of part of it.
	 * @param name the customer name or part of it.
	 * @return the customers that matches the request name.
	 */
	List<Customer> searchCustomers(String name);
}
