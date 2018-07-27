package com.alten.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alten.dto.AddressDTO;
import com.alten.dto.CustomerDTO;
import com.alten.model.Address;
import com.alten.model.Customer;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomerMapperImpl implements CustomerMapper {

	/**
	 * Utility for Object Conversion.
	 */
	@Autowired
	private final ModelMapper modelMapper;

	@Override
	public Customer convertToEntity(CustomerDTO customerDTO) {
		if (customerDTO.getAddress() == null)
			customerDTO.setAddress(new AddressDTO());
		Customer customer = modelMapper.map(customerDTO, Customer.class);
		customer.setAddress(convertToEntity(customerDTO.getAddress()));
		return customer;
	}

	@Override
	public CustomerDTO convertToDto(Customer customer) {
		if (customer.getAddress() == null)
			customer.setAddress(new Address());
		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
		customerDTO.setAddress(convertToDto(customer.getAddress()));
		return customerDTO;
	}

	@Override
	public Address convertToEntity(AddressDTO addressDTO) {
		return modelMapper.map(addressDTO, Address.class);
	}

	@Override
	public AddressDTO convertToDto(Address address) {
		return modelMapper.map(address, AddressDTO.class);
	}

}
