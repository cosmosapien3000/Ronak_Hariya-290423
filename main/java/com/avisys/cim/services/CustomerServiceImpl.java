package com.avisys.cim.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avisys.cim.Customer;
import com.avisys.cim.dao.CustomerDao;
import com.avisys.cim.payloads.RegisterDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

/* Autowired-> Dependency Injection at Runtime.  */
	
	@Autowired
	private CustomerDao customerdao;
	
	@Autowired
    private EntityManager entityManager;
	

	

	
	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerdao.findAll();
	}
	
/* Changes After Modification 3*/
	@Override
	public boolean register(RegisterDTO regdto) {
		System.out.println("Inside Customer-Service-Impl");
		Customer newCustomer=new Customer();
		
		// Check if user with this mobile number already exists
		for(String mobile:regdto.getMobileNumbers())
		{
			Query query = entityManager.createQuery("SELECT c FROM Customer c JOIN c.mobileNumbers m WHERE m = :mobile");
			query.setParameter("mobile", mobile);
			if(!query.getResultList().isEmpty())
				return false; /* This is a case of already existing Customer */
			
		}
		
		// Save the user to the database
       newCustomer.setFirstName(regdto.getFirstName());
       newCustomer.setLastName(regdto.getLastName());
       newCustomer.setMobileNumbers(regdto.getMobileNumbers());
       customerdao.save(newCustomer);
        System.out.println("User saved to Database successfully..!");
        return true;
        	
		
	}

	@Override
	public List<Customer> searchCustomerByFirstName(String firstName) {
		
		System.out.println("Inside Search by first Name Impl: "+firstName);
		// Use of Ready made implementations provided in JPA Repository
		List<Customer>customers= customerdao.findAll();
		List<Customer> matchedCustomers=new ArrayList<Customer>();
		for(Customer c:customers)
		{
			System.out.println(c.toString());
			if(c.getFirstName().toLowerCase().contains(firstName.toLowerCase()))
			{
				
				matchedCustomers.add(c); /* Added in List if Matched the requirement*/
			}
				
				
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
			
			if(c.getLastName().toLowerCase().contains(lastName.toLowerCase()))
				matchedCustomers.add(c); /* Added in List if Matched the requirement*/
				
		}
		return matchedCustomers;
		
	}

	@Override
	public List<Customer> searchCustomerByMobile(String mobile) {
		
		Query query = entityManager.createQuery("SELECT c FROM Customer c JOIN c.mobileNumbers m WHERE m = :mobile");
		query.setParameter("mobile", mobile);
		return query.getResultList();

	}
	
}
