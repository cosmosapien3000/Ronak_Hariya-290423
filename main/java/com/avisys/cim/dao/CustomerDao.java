package com.avisys.cim.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.avisys.cim.Customer;

import jakarta.transaction.Transactional;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
	
	//Customer findByMobileNumber(String mobileNumber);	
	@Query("INSERT INTO Customer (firstName, lastName, mobileNumbers) VALUES (:firstName, :lastName, :mobileNumbers)")
    @Modifying
    @Transactional
    void insertCustomer(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("mobileNumbers") List<String> mobileNumbers);

	
}
