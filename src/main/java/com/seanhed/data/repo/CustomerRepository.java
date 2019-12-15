package com.seanhed.data.repo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.seanhed.beans.Coupon;
import com.seanhed.beans.Customer;

@CrossOrigin(origins = "http://localhost:4200")
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public List<Customer> deleteByName(String name);

	public Customer findByName(String name);
	
	@Query("FROM Customer customer WHERE id = ?1")
	public Customer findById(long id);
	
	@Query("select customer from Customer customer join fetch customer.coupons c where c.id=?1")
	public List<Customer> findByCouponsLike(long id);
	

	

	

}
