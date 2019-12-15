package com.seanhed.data.controller;

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

import com.seanhed.beans.Company;
import com.seanhed.beans.Coupon;
import com.seanhed.beans.CouponType;
import com.seanhed.beans.Customer;
import com.seanhed.data.service.CompanyService;

@RestController
@RequestMapping("/company")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	// http://localhost:8080/company/getCoupon
	@GetMapping("/getCoupon")
	public ResponseEntity<Object> getCoupon(@RequestParam String token, @RequestParam long couponId) {
		return companyService.getCoupon(token, couponId);
	}

	// http://localhost:8080/company/getAllCoupons
	@GetMapping("/getAllCoupons")
	public ResponseEntity<Object> getAllCoupons(@RequestParam String token) {
		return companyService.getAllCoupons(token);
	}

	// http://localhost:8080/company/createCoupon
	@PostMapping("createCoupon")
	public ResponseEntity<Object> createCoupon(@RequestParam String token, @RequestBody Coupon coupon) {
		return companyService.createCoupon(token, coupon);
	}

	// http://localhost:8080/company/deleteCoupon
	@DeleteMapping("/deleteCoupon")
	public ResponseEntity<Object> deleteCouponByName(@RequestParam String token, @RequestParam String couponName) {
		return companyService.deleteCouponByName(token, couponName);
	}

	// http://localhost:8080/company/deleteCouponById
	@DeleteMapping("/deleteCouponById")
	public ResponseEntity<Object> deleteCouponById(@RequestParam String token, @RequestParam long couponId) {
		return companyService.deleteCouponById(token, couponId);
	}

	// http://localhost:8080/company/updateCoupon
	@PutMapping("/updateCoupon")
	public ResponseEntity<Object> updateCoupon(@RequestParam String token, @RequestParam long couponId,
			@RequestBody Coupon coupon) {
		return companyService.updateCoupon(token, couponId, coupon);
	}

	// http://localhost:8080/company/getCouponByType
	@GetMapping("/getCouponByType")
	public ResponseEntity<Object> getCouponByType(@RequestParam String token, @RequestParam CouponType type) {
		return companyService.findByType(token, type);
	}
}
