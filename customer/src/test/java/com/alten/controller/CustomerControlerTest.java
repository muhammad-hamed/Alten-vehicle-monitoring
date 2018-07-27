package com.alten.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.mockito.internal.util.reflection.FieldSetter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.alten.dto.AddressDTO;
import com.alten.dto.CustomerDTO;
import com.alten.mapper.CustomerMapper;
import com.alten.mapper.CustomerMapperImpl;
import com.alten.model.Customer;
import com.alten.service.impl.CustomerServiceImpl;
import com.alten.util.TestUtils;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerControlerTest {

	private MockMvc mockMvc;
	
	@Mock
	private CustomerMapper customerMapper;

	@Mock
	private CustomerServiceImpl customerServiceImpl;

	@InjectMocks
	private CustomerController customerController;
	
	private CustomerMapper localCustomerMapper = new CustomerMapperImpl(new ModelMapper());
	
	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
		FieldSetter.setField(customerController, customerController.getClass().getDeclaredField("customerMapper"), localCustomerMapper);
	}

	@Test
	public void testGetCustomers() throws Exception {
		Customer customer = new Customer();
		customer.setId(5l);
		List<Customer> customerList = Arrays.asList(customer);
	
		when(customerServiceImpl.getCustomers(any())).thenReturn(new PageImpl<>(customerList));

		mockMvc.perform(get("/customer")).andExpect(status().isOk()).andDo(print())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$..id").value(5));
	}

	@Test
	public void testGetCustomer() throws Exception {
		Customer customer = new Customer();
		customer.setId(5l);
		when(customerServiceImpl.getCustomerByID(any())).thenReturn(customer);

		mockMvc.perform(get("/customer/5")).andExpect(status().isOk()).andDo(print())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(content().json(new Gson().toJson(localCustomerMapper.convertToDto(customer))));
	}

	@Test
	public void testSave() throws Exception {
		CustomerDTO customer = new CustomerDTO();
		customer.setId(4l);
		customer.setName("name");

		AddressDTO address = new AddressDTO();
		address.setCountry("Egypt");
		address.setCity("Giza");
		address.setState("6th October");
		address.setAddressLine("ZayedSt");
		address.setPostalCode("12 961");

		customer.setAddress(address);
		
		when(customerServiceImpl.save(any())).thenReturn(localCustomerMapper.convertToEntity(customer));

		String jsonObj = TestUtils.asJsonString(customer);
		mockMvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON).content(jsonObj)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

}
