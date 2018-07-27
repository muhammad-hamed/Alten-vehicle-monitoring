package com.alten.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.alten.model.PingSignal;

@Repository
public interface PingSignalRepository extends PagingAndSortingRepository<PingSignal, Long> {
	
	public List<PingSignal> findByRegisterationNumber(String registerationNumber);

}
