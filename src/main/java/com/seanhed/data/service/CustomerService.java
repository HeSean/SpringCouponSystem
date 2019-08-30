package com.seanhed.data.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seanhed.beans.Customer;
import com.seanhed.data.repo.CustomerRepository;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	@PostConstruct
	public void initDB() {
		repository.deleteAll();
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer("Sean", "1234"));
		customers.add(new Customer("Michael", "1234"));
		customers.add(new Customer("Tomer", "1234"));
		customers.add(new Customer("Aurora", "1234"));
		customers.add(new Customer("Maya", "1234"));
		repository.save(customers);
	}
	
	public Customer getCustomer(long id) {
		return repository.getOne(id);
	}
	
	public List<Customer> getCustomers(){
		return repository.findAll();
	}
	
	public Customer addCustomer(Customer customer) {
		return repository.save(customer);
	}
	
	public List<Customer>deleteCustomerByName(String name){
		return repository.deleteByName(name);
	}
	
	public Customer updateCustomer(long id, Customer customer) {
		return repository.updateCustomer(id, customer);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
