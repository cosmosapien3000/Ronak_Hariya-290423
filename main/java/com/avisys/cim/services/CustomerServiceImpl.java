package com.avisys.cim.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avisys.cim.Customer;
import com.avisys.cim.exceptions.InvalidInputException;
import com.avisys.cim.exceptions.NoCustomerFoundException;
import com.avisys.cim.payloads.CustomerDTO;
import com.avisys.cim.payloads.RegisterDTO;
import com.avisys.cim.payloads.UpdateDTO;
import com.avisys.cim.repository.CustomerRepository;
import com.avisys.cim.repository.CustomerRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	/* Autowired-> Dependency Injection at Runtime.  */
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
    private EntityManager entityManager;
	
	@Autowired
	private ModelMapper modelMapper;
	

	
	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepo.findAll();
	}



	@Override
	public List<Customer> searchCustomerByFirstName(String word) {
		
		List<Customer> list=this.customerRepo.searchByFirstName("%"+word+"%");
		return list;
		
	}



	@Override
	public List<Customer> searchCustomerByLastName(String word) {
		List<Customer>list=this.customerRepo.searchBylastName("%"+word+"%");
		return list;
	}



	@Override
	public Customer searchCustomerByMobile(String mobile) {
		Customer customer=this.customerRepo.searchByMobileNumber(mobile).orElseThrow(()->new NoCustomerFoundException("No such record found"));
		return customer;
	}



	@Override
	public boolean register(RegisterDTO registerDTO) {
		// Corner cases
		if(registerDTO.getFirstName().equals("")||registerDTO.getLastName().equals(""))
		{
			throw new InvalidInputException("First name or Last name can't be blank");
		}
		if(registerDTO.getMobileNumbers().isEmpty())
		{
			throw new InvalidInputException("Please register with a mobile number");
		}
		registerDTO.getMobileNumbers().toString();
		for(String mobile:registerDTO.getMobileNumbers())
		{
			if(mobile.length()!=10)
			{
				throw new InvalidInputException("Mobile number must be of 10 digits");
			}
			if(customerRepo.searchByMobileNumber(mobile).isPresent())
			{
				return false;
			}
		}
		Customer newCustomer=new Customer();
		newCustomer.setFirstName(registerDTO.getFirstName());
		newCustomer.setLastName(registerDTO.getLastName());
		newCustomer.setMobileNumbers(registerDTO.getMobileNumbers());
		this.customerRepo.save(newCustomer);
		return true;
	}



	@Override
	public void deleteCustomer(String mobile) {
		if(mobile.equals("")||mobile.length()!=10)
		{
			throw new InvalidInputException("Please provide valid mobile number");
		}
		Customer customer=this.customerRepo.searchByMobileNumber(mobile).orElseThrow(()->new NoCustomerFoundException("No Customer found"));
		this.customerRepo.delete(customer);
	}
	
	@Override
	public boolean addAlternateMobile(long id,String mobile) {
		
		Customer oldCustomer=customerRepo.findById(id).orElseThrow(()->new NoCustomerFoundException("No Customer found"));
		if(oldCustomer!=null)
		{
			System.out.println("Numbers Associated before :"+oldCustomer.getMobileNumbers().toString());
			System.out.println("New Number :"+mobile);
			oldCustomer.getMobileNumbers().add(mobile);
			System.out.println("Numbers Associated After:"+oldCustomer.getMobileNumbers().toString());

			return true;
			
		}
		// If a Customer is InValid!
		return false;
	}

	@Override
	public boolean removeAlternateMobile(long id,String mobile) {
		Customer oldCustomer=customerRepo.findById(id).orElseThrow(()->new NoCustomerFoundException("No Customer found"));;
		if(oldCustomer!=null)
		{
			System.out.println("Numbers Associated before :"+oldCustomer.getMobileNumbers().toString());
			System.out.println("New Number :"+mobile);
			//Trim the List to remove mobile number
			List <String> revisedList=oldCustomer.getMobileNumbers().stream().filter(m->!m.equals(mobile)).collect(Collectors.toList());
			System.out.println(revisedList.toString());
			oldCustomer.setMobileNumbers(revisedList);
			System.out.println("Numbers Associated After:"+oldCustomer.getMobileNumbers().toString());

			return true;
			
		}
		// If a Customer is InValid!
		return false;
	}
	
	
	
}
