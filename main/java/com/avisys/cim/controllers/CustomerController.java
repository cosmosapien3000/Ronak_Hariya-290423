package com.avisys.cim.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avisys.cim.Customer;
import com.avisys.cim.payloads.ApiResponse;
import com.avisys.cim.payloads.RegisterDTO;
import com.avisys.cim.payloads.UpdateDTO;
import com.avisys.cim.repository.CustomerRepository;
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
		List <Customer> customers=customerService.getAllCustomers();
		return new ResponseEntity(customers,HttpStatus.OK);
	}

	/* To Filter  Customer by paramter */
	
	@GetMapping(value="/getCustomer",params= {"firstName"})
	public ResponseEntity<List<Customer>> getCustomerByFirstName(@RequestParam ("firstName") String firstName )
	{
		List<Customer> customers=customerService.searchCustomerByFirstName(firstName);
		if(customers.isEmpty())
			return ResponseEntity.notFound().build();
		
		else
			return ResponseEntity.ok(customers);
		
	}
	
	@RequestMapping(value="/getCustomer",params= {"lastName"})
	public ResponseEntity<List<Customer>> getCustomerByLastName(@RequestParam ("lastName") String lastName )
	{
		
		List<Customer> customers=customerService.searchCustomerByLastName(lastName);
		if(customers.isEmpty())
			return ResponseEntity.notFound().build();
		
		else
			return ResponseEntity.ok(customers);
		
	}
	
	@RequestMapping(value="/getCustomer",params= {"mobile"})
	public ResponseEntity<Customer> getCustomerByMobile(@RequestParam ("mobile") String mobile )
	{
		
		Customer customer=customerService.searchCustomerByMobile(mobile);
		if(customer!=null)
			return ResponseEntity.ok(customer);
		
		else
			return ResponseEntity.notFound().build();
		
		
	}
	
	/* To Register new customers */
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterDTO customerdto)
	{
		System.out.println("inside customer controller and register mapping");
		
		boolean createdCustomer=this.customerService.register(customerdto);
		
		if(createdCustomer==true)
		{
			return new ResponseEntity(new ApiResponse("Registration successful..!",true),HttpStatus.CREATED);
		}
		
		return new ResponseEntity(new ApiResponse("Please provide valid credentials..!",false),HttpStatus.CONFLICT);
    }

	/* Delete a Customer */
	
	@DeleteMapping(value="/delete",params= {"mobile"})
	public ResponseEntity<?> deleteCustomer(@RequestParam ("mobile") String mobile)
	{
		System.out.println("Inside Customer Controller - delete Method");
		this.customerService.deleteCustomer(mobile);
		
		
			return new ResponseEntity(new ApiResponse("Customer Deleted successfully.!",true),HttpStatus.ACCEPTED);

		
		
	}
	
	/*Partial Update*/
	
	@PatchMapping("addMobile/{id}")
		    public ResponseEntity<?> addMobileNumber(@PathVariable Long id,@RequestParam String mobile){
		    	//calling the service method with received request parameter.
		    	if(this.customerService.addAlternateMobile(id, mobile)==true)
		    	{
					return new ResponseEntity(new ApiResponse("New mobile number added successfully.!",true),HttpStatus.ACCEPTED);

		    	}
		    	
					return new ResponseEntity(new ApiResponse("Mobile Number already exists!",false),HttpStatus.BAD_REQUEST);

		    	
		    }
			
		    
	@PatchMapping("removeMobile/{id}")
		    public ResponseEntity<?> deleteMobileNumber(@PathVariable Long id,@RequestParam String mobile){
		    	//calling the service method with received request parameter.
		if(this.customerService.removeAlternateMobile(0, mobile)==true)
		{
			return new ResponseEntity(new ApiResponse("Mobile number removed successfully.!",true),HttpStatus.ACCEPTED);

		}
		return new ResponseEntity(new ApiResponse("Such mobile does not exists!",false),HttpStatus.BAD_REQUEST);

		    	
		}

}
