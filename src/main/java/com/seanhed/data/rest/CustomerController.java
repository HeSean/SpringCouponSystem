package com.seanhed.data.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seanhed.beans.Coupon;
import com.seanhed.beans.Customer;
import com.seanhed.data.service.CustomerService;

@RestController
@RequestMapping("/customer/")
public class CustomerController {

	@Autowired
	private CustomerService service;

	// http://localhost:8080/customer/getCustomer/1
	@GetMapping("/getCustomer/{id}")
	public Customer getCustomer(@PathVariable long id) {
		return service.getCustomer(id);
	}

	// http://localhost:8080/customer/getAll
	@GetMapping("getAll")
	public List<Customer> getCustomers() {
		return service.getCustomers();
	}

	// http://localhost:8080/customer/add
	@PostMapping("add")
	public Customer addCustomer(@RequestBody Customer customer) {
		return service.addCustomer(customer);
	}

	// http://localhost:8080/customer/buyCoupon
	@PostMapping("/buyCoupon/{id}")
	public String buyCoupon(@PathVariable long custID, @RequestBody Coupon coupon) {
		System.out.println("buyCoupon in CustomerController ...  = " + coupon);
		return service.buyCoupon(custID, coupon);
	}

	// http://localhost:8080/customer/delete/Maya
	@DeleteMapping("/delete/{name}")
	public List<Customer> deleteCustomer(@PathVariable String name) {
		return service.deleteCustomerByName(name);
	}

	// http://localhost:8080/customer/update/1
	@PutMapping("/update/{id}")
	public Customer updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
		return service.updateCustomer(id, customer);
	}
}
