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
	public  ResponseEntity<?> getCustomer(@PathVariable long id) {
		Customer customer =service.getCustomer(id);
		System.out.println("getCustomer - " + customer);
		return ResponseEntity.ok().body(customer);
	}

	// http://localhost:8080/customer/getAll
	@GetMapping("getAll")
	public ResponseEntity<List<Customer>> getCustomers() {
		List<Customer> customers = service.getCustomers();
		System.out.println("customer list - " + customers);
		return ResponseEntity.ok().body(customers);
	}

	// http://localhost:8080/customer/add
	@PostMapping("add")
	public  ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		System.out.println("1              savedCustomer is - " + customer);
		Customer savedCustomer =  service.addCustomer(customer);
		System.out.println("2             savedCustomer is - " + customer);
		return ResponseEntity.ok().body(savedCustomer);
	}

	// http://localhost:8080/customer/buyCoupon
	@PostMapping("/buyCoupon/{id}")
	public ResponseEntity<?> buyCoupon(@PathVariable long id, @RequestBody long couponID) {
		Customer customer =  service.buyCoupon(id, couponID);
		System.out.println("buyCoupon in CustomerController of customer " + customer);
		return ResponseEntity.ok().body(customer);
	}

	// http://localhost:8080/customer/delete/Maya
	@DeleteMapping("/delete/{name}")
	public ResponseEntity<List<Customer>> deleteCustomer(@PathVariable String name) {
		List<Customer> customers = service.deleteCustomerByName(name);
		System.out.println("list of customer by name - " + customers);
		return ResponseEntity.ok().body(customers);
	}

	// http://localhost:8080/customer/update/1
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
		Customer updatedCustomer =  service.updateCustomer(id, customer);
		System.out.println("updatedCustomer is - " + updatedCustomer);
		return ResponseEntity.ok().body(updatedCustomer);
	}
}
