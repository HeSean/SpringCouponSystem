package com.seanhed.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seanhed.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public List<Customer> deleteByName(String name);

	public Customer updateCustomer(long id, Customer customer);

}
