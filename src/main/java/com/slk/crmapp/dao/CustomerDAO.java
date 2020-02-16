package com.slk.crmapp.dao;

import java.util.Map;

import com.slk.crmapp.entity.Customer;

public interface CustomerDAO {

	public Map<Integer, Customer> getCustomers();

	public void saveCustomer(Integer customerId, Customer theCustomer);

	public Customer getCustomer(Integer customerId);

	public void deleteCustomer(Integer customerId);

	public void updateCustomer(Integer customerId, Customer theCustomer);
	
}
