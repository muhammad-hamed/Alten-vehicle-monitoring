package com.alten.mapper;

import com.alten.dto.AddressDTO;
import com.alten.dto.CustomerDTO;
import com.alten.model.Address;
import com.alten.model.Customer;

/**
 * The conversion and mapping between the customer Entity and the DTO.
 * 
 * @author muhammad hamed
 *
 */
public interface CustomerMapper {

	/**
	 * Convert the customer DTO into an Entity Object.
	 * @param customerDTO Customer DTO.
	 * @return CustomerEntity.
	 */
	Customer convertToEntity(CustomerDTO customerDTO);

	/**
	 * Convert the Customer Entity to Customer DTO Object.
	 * @param customer CustomerEntity.
	 * @return Customer DTO Object
	 */
	CustomerDTO convertToDto(Customer customer);

	/**
	 * Convert from Address DTO Object to Address Entity Object.
	 * @param addressDTO Address DTO Object.
	 * @return Address Entity Object.
	 */
	Address convertToEntity(AddressDTO addressDTO);

	/**
	 * Convert from Address Entity Object to Address DTO Object.
	 * @param address Address Entity Object.
	 * @return Address DTO Object.
	 */
	AddressDTO convertToDto(Address address);
}
