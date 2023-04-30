package com.avisys.cim.services;

import com.avisys.cim.Customer;
import com.avisys.cim.controllers.*;
import com.avisys.cim.payloads.RegisterDTO;
import com.avisys.cim.payloads.UpdateDTO;

import java.util.List;

/* Design Specifications */
public interface CustomerService {


	

	public boolean register(RegisterDTO regdto);
	
	public List<Customer> getAllCustomers();

	public List<Customer> searchCustomerByFirstName(String firstName);

	public List<Customer> searchCustomerByLastName(String lastName);

	public List<Customer> searchCustomerByMobile(String mobile);

	public boolean deleteCustomer(String mobile);

	public boolean addAlternateMobile(int id,String mobile);

	public boolean removeAlternateMobile(int id,String mobile);

	
	
	
}
