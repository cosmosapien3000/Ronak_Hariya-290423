package com.avisys.cim.payloads;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;

/*
 DTO: Standard Design practice to avoid actual data expose to the Client from the Server  
 
 */

public class RegisterDTO {

	private String firstName;

	
	private String lastName;

	
	@ElementCollection
    private List<String> mobileNumbers;
	

	

	public RegisterDTO() {
		super();
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public List<String> getMobileNumbers() {
		return mobileNumbers;
	}




	public void setMobileNumbers(List<String> mobileNumbers) {
		this.mobileNumbers = mobileNumbers;
	}


	
	
}
