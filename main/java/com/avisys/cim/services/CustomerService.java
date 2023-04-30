package com.avisys.cim.services;

import com.avisys.cim.Customer;
import com.avisys.cim.controllers.*;
import com.avisys.cim.payloads.RegisterDTO;
import java.util.List;

/* Design Specifications */
public interface CustomerService {


	
	
	
	public List<Customer> getAllCustomers();

	public List<Customer> searchCustomerByFirstName(String firstName);

	public List<Customer> searchCustomerByLastName(String lastName);

	public List<Customer> searchCustomerByMobile(String mobile);

	
	
	
}
