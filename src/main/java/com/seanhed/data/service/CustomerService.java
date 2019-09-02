package com.seanhed.data.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seanhed.beans.Coupon;
import com.seanhed.beans.Customer;
import com.seanhed.data.repo.CouponRepository;
import com.seanhed.data.repo.CustomerRepository;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CouponRepository couponRepository;

	@PostConstruct
	public void initDB() {
		customerRepository.deleteAll();
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer("Sean", "1234"));
		customers.add(new Customer("Michael", "1234"));
		customers.add(new Customer("Tomer", "1234"));
		customers.add(new Customer("Aurora", "1234"));
		customers.add(new Customer("Maya", "1234"));
		customerRepository.save(customers);
	}
	
	public Customer getCustomer(long id) {
		return customerRepository.getOne(id);
	}
	
	public List<Customer> getCustomers(){
		return customerRepository.findAll();
	}
	
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Coupon buyCoupon(long custID,Coupon coupon) {
		Coupon newCoupon = couponRepository.getOne(coupon.getId());
		newCoupon.setAmount(coupon.getAmount()-1);
		couponRepository.save(newCoupon);
		return customerRepository.buyCoupon(custID, coupon.getId());
	}
	
	public List<Customer>deleteCustomerByName(String name){
		return customerRepository.deleteByName(name);
	}
	
	public Customer updateCustomer(long id, Customer customer) {
		return null;
		//return repository.updateCustomer(id, customer);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
