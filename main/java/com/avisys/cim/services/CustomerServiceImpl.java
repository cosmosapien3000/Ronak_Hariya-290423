package com.avisys.cim.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avisys.cim.Customer;
import com.avisys.cim.dao.CustomerDao;
import com.avisys.cim.payloads.RegisterDTO;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

/* Autowired-> Dependency Injection at Runtime.  */
	
	@Autowired
	private CustomerDao customerdao;
	
	@Override
	public boolean register(RegisterDTO regdto) {
		System.out.println("Inside Customer-Service-Impl");
		Customer newCustomer=new Customer();
		
		// Check if user with this mobile number already exists
		Customer existingUser = customerdao.findByMobileNumber(regdto.getMobileNumber());
        if (existingUser != null) {
            return false;
        }
		
        else
        // Save the user to the database
        newCustomer=dtoToCust(regdto);
        customerdao.save(newCustomer);
        System.out.println("User saved to Database successfully..!");
        return true;
        	
		
	}

/* Helper Method to Convert DTO -> Transient Entity. We can also use Model-Mapper for the same. */
	public Customer dtoToCust(RegisterDTO regdto)
	{
		Customer newCustomer=new Customer();
		newCustomer.setFirstName(regdto.getFirstName());
		newCustomer.setLastName(regdto.getLastName());
		newCustomer.setMobileNumber(regdto.getMobileNumber());
		
		System.out.println("DTO conversion success.!");
		return newCustomer;
		
		
	}
	
	
	
	
	
	@Override
	public List<Customer> getAllCustomers() {
		// Use of Ready made implementations provided in JPA Repository
		return customerdao.findAll();
		
	}

	@Override
	public List<Customer> searchCustomerByFirstName(String firstName) {
		
		// Use of Ready made implementations provided in JPA Repository
		List<Customer>customers= customerdao.findAll();
		List<Customer> matchedCustomers=new ArrayList<Customer>();
		for(Customer c:customers)
		{
			if(c.getFirstName().contains(firstName.toLowerCase()))
				matchedCustomers.add(c); /* Added in List if Matched the requirement*/
				
		}
		return matchedCustomers;
			
		
	}

	@Override
	public List<Customer> searchCustomerByLastName(String lastName) {
		
		// Use of Ready made implementations provided in JPA Repository
		List<Customer>customers= customerdao.findAll();
		List<Customer> matchedCustomers=new ArrayList<Customer>();
		for(Customer c:customers)
		{
			if(c.getLastName().contains(lastName.toLowerCase()))
				matchedCustomers.add(c); /* Added in List if Matched the requirement*/
				
		}
		return matchedCustomers;
		
	}

	@Override
	public List<Customer> searchCustomerByMobile(String mobile) {
		
		// Use of Ready made implementations provided in JPA Repository
		List<Customer>customers= customerdao.findAll();
		List<Customer> matchedCustomers=new ArrayList<Customer>();
		for(Customer c:customers)
		{
			if(c.getMobileNumber().equals(mobile))
				matchedCustomers.add(c); /* Added in List if Matched the requirement*/
				
		}
		return matchedCustomers;
	}

}
