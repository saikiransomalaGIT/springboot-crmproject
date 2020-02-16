package com.slk.crmapp.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.slk.crmapp.entity.Customer;
import com.slk.crmapp.rest.CustomerNotFoundException;

@Component
public class CustomerDAOImpl implements CustomerDAO {

	private Map<Integer, Customer> theCustomers;

	// define @PostConstruct to load the student data ... only once!

	@PostConstruct
	public void loadData() {

		theCustomers = new HashMap<Integer, Customer>();

		theCustomers.put(1, new Customer(1, "Poornima", "Patel", "patel@slkgroup.com"));
		theCustomers.put(2, new Customer(2, "Mario", "Rossi", "rossi@slkgroup.com"));
		theCustomers.put(3, new Customer(3, "Mary", "Smith", "smith@slkgroup.com"));
	}

	@Override
	public Map<Integer, Customer> getCustomers() {

		return theCustomers;
	}

	@Override
	public void saveCustomer(Integer customerId, Customer theCustomer) {

		if (theCustomers.containsKey(customerId)) {
			throw new CustomerNotFoundException("Customer already exist with customer id = " + theCustomer.getId());
		} else {
			theCustomers.put(customerId, theCustomer);
		}
	}

	@Override
	public Customer getCustomer(Integer customerId) {

		Customer tempCustomer = theCustomers.get(customerId);

		if (tempCustomer == null) {
			throw new CustomerNotFoundException("Customer not found with customer id = " + customerId);
		}

		return tempCustomer;
	}

	@Override
	public void updateCustomer(Integer customerId, Customer theCustomer) {

		if (theCustomers.containsKey(customerId)) {
			theCustomers.put(customerId, theCustomer);
		} else {
			throw new CustomerNotFoundException("Customer does not exist with customer id = " + theCustomer.getId());
		}
	}

	@Override
	public void deleteCustomer(Integer theId) {

		if (theCustomers.containsKey(theId)) {
			theCustomers.remove(theId);
		} else {
			throw new RuntimeException("Requested Customer not found with Id = " + theId);
		}
	}

}
