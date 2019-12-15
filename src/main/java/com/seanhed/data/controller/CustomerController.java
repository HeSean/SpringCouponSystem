package com.seanhed.data.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

	// http://localhost:8080/customer/buyCoupon
	@PostMapping("/buyCoupon")
	public ResponseEntity<Object> buyCoupon(@RequestParam String token,
			@RequestParam long couponId) {
		return service.buyCoupon(token,couponId);
	}

	// http://localhost:8080/customer/getPurchasedCouponByPrice
	@GetMapping("/getPurchasedCouponByPrice")
	public ResponseEntity<Object> getPurchasedCouponByPrice(@RequestParam String token, @RequestParam double price) {
		return service.findByPrice(token, price);
	}

	// http://localhost:8080/customer/getPurchasedCouponByDate
	@GetMapping("/getPurchasedCouponByDate")
	public ResponseEntity<Object> getPurchasedCouponByDate(@RequestParam String token,
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
		return service.findByDate(token, date);
	}

}
