package com.alten.service;
/**
 * Customer vehicle management of the customer related actions
 * @author muhammad hamed
 *
 */
public interface CustomerVehicleService {
	/**
	 * Delete the customer vehicles in case of customer deletion.
	 * @param customerID the Customer identifier.
	 */
	public void deleteCustomerVehicles(Long customerID);
}
