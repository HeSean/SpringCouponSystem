package com.seanhed.data.repo;

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

	//public Customer updateCustomer(long id, Customer customer);
	
//	@Query("INSERT INTO customers_coupon (customer_id, coupon_id) VALUES (customerID,couponID)")
//	public Coupon buyCoupon(@Param("customerID") long customerID, @Param("couponID") long couponID);

}
