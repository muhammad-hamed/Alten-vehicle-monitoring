package com.alten.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alten.model.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
	@Query("SELECT c from Customer c where c.name LIKE  CONCAT( '%', :name, '%') ")
	List<Customer> findCustomerByPartOfName(@Param("name") String name);
}
