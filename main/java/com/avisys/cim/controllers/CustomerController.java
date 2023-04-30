package com.avisys.cim.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avisys.cim.Customer;
import com.avisys.cim.dao.CustomerDao;
import com.avisys.cim.payloads.ApiResponse;
import com.avisys.cim.payloads.RegisterDTO;
import com.avisys.cim.services.CustomerService;


@RestController
@RequestMapping("/com.myApp")
public class CustomerController {

/* Autowired-> Dependency Injection at Runtime.  */
	
	@Autowired
	private CustomerService customerService;	
	
	
/* Trial Api Call:To Check whether all works perfectly fine( Client-->Server Flow) 
 * Here we are using Postman for debugging API's */
 
	@GetMapping("/dummy")
	public ResponseEntity<?> dummyRequestHandle()
	{
		System.out.println("Inside dummy method");
		return new ResponseEntity(new ApiResponse("Success.!Go ahead!",true),HttpStatus.OK);
	} 
	

		
	
	



/* To Get All Customer*/
	
	@GetMapping("/allCustomers")
	public ResponseEntity<List<Customer>> showClaimList()
	{
		System.out.println("Inside customer controller & customers list method");
		List <Customer> customers=customerService.getAllCustomers();
		return new ResponseEntity(customers,HttpStatus.OK);
	}

/* To Filter  Customer by paramter */
	
	@GetMapping(value="/getCustomer",params= {"firstName"})
	public ResponseEntity<List<Customer>> getCustomerByFirstName(@RequestParam ("firstName") String firstName )
	{
		System.out.println("Inside customer controller & find By First-Name method");
		List<Customer> customers=customerService.searchCustomerByFirstName(firstName);
		if(customers.isEmpty())
			return ResponseEntity.notFound().build();
		
		else
			return ResponseEntity.ok(customers);
		
	}
	
	@RequestMapping(value="/getCustomer",params= {"lastName"})
	public ResponseEntity<List<Customer>> getCustomerByLastName(@RequestParam ("lastName") String lastName )
	{
		System.out.println("Inside customer controller & find By Last-Name method");
		List<Customer> customers=customerService.searchCustomerByLastName(lastName);
		if(customers.isEmpty())
			return ResponseEntity.notFound().build();
		
		else
			return ResponseEntity.ok(customers);
		
	}
	
	@RequestMapping(value="/getCustomer",params= {"mobile"})
	public ResponseEntity<List<Customer>> getCustomerByMobile(@RequestParam ("mobile") String mobile )
	{
		System.out.println("Inside customer controller & find By Mobile Number method");
		List<Customer> customers=customerService.searchCustomerByMobile(mobile);
		if(customers.isEmpty())
			return ResponseEntity.notFound().build();
		
		else
			return ResponseEntity.ok(customers);
		
		
	}
	


}
