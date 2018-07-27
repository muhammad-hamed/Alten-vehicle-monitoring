package com.alten.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alten.model.Address;
import com.alten.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
@SpringBootTest
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager em;

	@Test
	public void testInsertCustomer() throws SQLException {
		Customer myTestCustomer = new Customer();
		myTestCustomer.setName("Hamed");
		Address address = new Address();
		address.setCountry("Egypt");
		address.setCity("Giza");
		address.setState("6th October");
		address.setAddressLine("ZayedSt");
		address.setPostalCode("12 961");
		myTestCustomer.setAddress(address);
		Customer insertedTestCustomer = customerRepository.save(myTestCustomer);

		String customerName = jdbcTemplate.queryForObject("SELECT NAME FROM CUSTOMER WHERE ID = ? ", String.class,
				insertedTestCustomer.getId());
		assertThat(customerName, equalTo(myTestCustomer.getName()));
	}
	
	
	@Test
	public void testFindCustomerByPartOfName() throws SQLException {
		Customer myTestCustomer = new Customer();
		myTestCustomer.setName("Hamed");
		Address address = new Address();
		address.setCountry("Egypt");
		address.setCity("Giza");
		address.setState("6th October");
		address.setAddressLine("ZayedSt");
		address.setPostalCode("12 961");
		myTestCustomer.setAddress(address);
		customerRepository.save(myTestCustomer);
		
		List<Customer> customers = customerRepository.findCustomerByPartOfName("Ham");

		System.out.println("Result : " + customers);
		assertThat(customers.size(), equalTo(1));
	}

}
