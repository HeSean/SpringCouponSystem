package com.seanhed.data.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	@Autowired
	private CustomerService service;

	// http://localhost:8080/customer/getCustomer/1
	@GetMapping("/getCustomer/{id}")
	public  ResponseEntity<Object> getCustomer(@PathVariable long id) {
		return service.getCustomer(id);
	}

	// http://localhost:8080/customer/getAll
	@GetMapping("getAll")
	public ResponseEntity<List<Object>> getCustomers() {
		return service.getCustomers();
	}

	// http://localhost:8080/customer/addCustomer
	@PostMapping("addCustomer")
	public  ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
		return service.addCustomer(customer);
	}

	// http://localhost:8080/customer/buyCoupon/{customerId}
	@PostMapping("/buyCoupon/{id}")
	public ResponseEntity<Object> buyCoupon(@PathVariable long id, @RequestBody long couponID) {
		return service.buyCoupon(id, couponID);
	}

	// http://localhost:8080/customer/deleteCustomer/Maya
	@DeleteMapping("/deleteCustomer/{name}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable String name) {
		return service.deleteCustomerByName(name);
	}

	// http://localhost:8080/customer/updateCustomer/1
	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<Object> updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
		return service.updateCustomer(id, customer);
	}
}
