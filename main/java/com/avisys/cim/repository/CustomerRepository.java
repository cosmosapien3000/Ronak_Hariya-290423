package com.avisys.cim.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.avisys.cim.Customer;

import jakarta.transaction.Transactional;


@Transactional
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("Select c from Customer c")
	public List<Customer> findAllCustomers();
	
	@Query("Select c FROM Customer c WHERE lower(c.firstName) LIKE lower(:key)")
	List<Customer> searchByFirstName(@Param("key") String firstName );
	
	@Query("Select c FROM Customer c WHERE lower(c.lastName) LIKE lower(:key)")
	List<Customer> searchBylastName(@Param("key") String lastName );
	
	@Query("SELECT c FROM Customer c join c.mobileNumbers m WHERE m = :mobileNumber")
    Optional<Customer> searchByMobileNumber(@Param("mobileNumber")String mobileNumber);
	
	//Customer findById(int  id);
}
