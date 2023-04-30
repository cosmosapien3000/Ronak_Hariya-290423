package com.avisys.cim;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;
	
	

/* Modification After Requirement 2 */
	@ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "mobile_numbers", joinColumns= @JoinColumn(name ="customer_id"))
    @Column(name = "mobile_number")
    private List<String> mobileNumbers=new ArrayList<String>();

	public Customer(Long id, String firstName, String lastName, List<String> mobileNumbers) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumbers = mobileNumbers;
	}

	public Customer() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
