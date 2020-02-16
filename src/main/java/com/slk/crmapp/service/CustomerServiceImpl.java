package com.slk.crmapp.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slk.crmapp.dao.CustomerDAO;
import com.slk.crmapp.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	// need to inject customer dao
	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public Map<Integer, Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	public void saveCustomer(Integer customerId, Customer theCustomer) {

		customerDAO.saveCustomer(customerId, theCustomer);
	}

	@Override
	public Customer getCustomer(Integer theId) {

		return customerDAO.getCustomer(theId);
	}

	@Override
	public void deleteCustomer(Integer theId) {

		customerDAO.deleteCustomer(theId);
	}

	@Override
	public void updateCustomer(Integer customerId, Customer theCustomer) {
		
		customerDAO.updateCustomer(customerId, theCustomer);
	}

}
