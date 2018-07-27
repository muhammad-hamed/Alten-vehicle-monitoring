package com.alten.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.alten.model.Customer;
import com.alten.repository.CustomerRepository;
import com.alten.service.impl.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerService;

	@Test
	public void testGetCustomerByID() {
		Customer customer = getCustomer();
		when(customerRepository.findById(1l)).thenReturn(Optional.of(customer));

		Customer retrunedCustomer = customerService.getCustomerByID(1l);
		assertEquals(customer, retrunedCustomer);
	}

	@Test
	public void testSave() {
		Customer customer = getCustomer();
		when(customerRepository.save(customer)).thenReturn(customer);

		customerService.save(customer);
	}

	@Test
	public void testGetCustomers() {
		Customer customer = getCustomer();
		when(customerRepository.findAll(any(Pageable.class)))
				.thenReturn(new PageImpl<>(Arrays.asList(customer, customer)));

		Page<Customer> retrunedCustomers = customerService.getCustomers(PageRequest.of(0, Integer.MAX_VALUE));
		assertEquals(2, retrunedCustomers.getNumberOfElements());
	}

	/**
	 * Helper method.
	 * 
	 * @return
	 */
	private Customer getCustomer() {
		Customer customer = new Customer();
		customer.setId(1l);
		customer.setName("Hamed");
		return customer;
	}

}
