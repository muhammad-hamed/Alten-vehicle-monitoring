package com.alten.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alten.model.Vehicle;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
@SpringBootTest
public class VehicleRepositoryTest {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager em;

	@Test
	public void testInsertVehicle() throws SQLException {
		Vehicle myTestVehicle = new Vehicle();
		myTestVehicle.setVehicleID("12345678901234567");
		myTestVehicle.setRegisterationNumber("123456");
		myTestVehicle.setCustomerID(5l);

		Vehicle insertedTestVehicle = vehicleRepository.save(myTestVehicle);

		String vehicleID = jdbcTemplate.queryForObject("SELECT VEHICLE_ID FROM VEHICLE WHERE ID = ? ", String.class,
				insertedTestVehicle.getId());
		assertThat(vehicleID, equalTo(myTestVehicle.getVehicleID()));
	}

	@Test
	public void testFindByVehicleID() throws SQLException {
		Vehicle myTestVehicle = new Vehicle();
		myTestVehicle.setVehicleID("12345678901234567");
		myTestVehicle.setRegisterationNumber("123456");
		myTestVehicle.setCustomerID(5l);

		vehicleRepository.save(myTestVehicle);

		Vehicle returnedVehicle = vehicleRepository.findByRegisterationNumber(myTestVehicle.getRegisterationNumber()).get();

		assertThat(myTestVehicle.getVehicleID(), equalTo(returnedVehicle.getVehicleID()));
	}

}
