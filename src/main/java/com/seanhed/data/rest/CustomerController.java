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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seanhed.beans.Coupon;
import com.seanhed.beans.Customer;
import com.seanhed.data.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	@Autowired
	private CustomerService service;

	// http://localhost:8080/customer/getCustomer
	@GetMapping("/getCustomer")
	public  ResponseEntity<Object> getCustomer(@RequestParam long customerId) {
		return service.getCustomer(customerId);
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

	// http://localhost:8080/customer/buyCoupon
	@PostMapping("/buyCoupon")
	public ResponseEntity<Object> buyCoupon(@RequestParam long customerId, @RequestParam long couponId) {
		return service.buyCoupon(customerId, couponId);
	}

	// http://localhost:8080/customer/deleteCustomer
	@DeleteMapping("/deleteCustomer")
	public ResponseEntity<Object> deleteCustomer(@RequestParam String customerName) {
		return service.deleteCustomerByName(customerName);
	}

	// http://localhost:8080/customer/updateCustomer
	@PutMapping("/updateCustomer")
	public ResponseEntity<Object> updateCustomer(@RequestParam long customerId, @RequestBody Customer customer) {
		return service.updateCustomer(customerId, customer);
	}
}
