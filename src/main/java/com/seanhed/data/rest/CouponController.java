package com.seanhed.data.rest;

import java.util.Collection;
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

import com.seanhed.beans.Company;
import com.seanhed.beans.Coupon;
import com.seanhed.beans.CouponType;
import com.seanhed.data.service.CompanyService;
import com.seanhed.data.service.CouponService;
import com.seanhed.data.service.CustomerService;

@RestController
@RequestMapping("/coupon/")
@CrossOrigin(origins = "http://localhost:4200")
public class CouponController {

	@Autowired
	private CouponService couponService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CustomerService customerService;

	// http://localhost:8080/coupon/getCoupon
	@GetMapping("/getCoupon")
	public ResponseEntity<Object> getCoupon(@RequestParam long couponId) {
		return couponService.getCoupon(couponId);
	}

	// http://localhost:8080/coupon/getAll
	@GetMapping("/getAll")
	public ResponseEntity<List<Object>> getCoupons() {
		return couponService.getCoupons();
	}

	// http://localhost:8080/coupon/addCoupon
	@PostMapping("addCoupon")
	// @RequestMapping(path="add",method = RequestMethod.POST, consumes =
	// MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> addCoupon(@RequestBody Coupon coupon) {
		return couponService.addCoupon(coupon);
	}

	// http://localhost:8080/coupon/deleteCoupon
	@DeleteMapping("/deleteCoupon")
	public ResponseEntity<Object> deleteCouponByName(
			@RequestParam String couponName
			) {
		return couponService.deleteCouponByName(couponName);
	}

	// http://localhost:8080/coupon/deleteCouponById
	@DeleteMapping("/deleteCouponById")
	public ResponseEntity<Object> deleteCouponById(@RequestParam long couponId) {
		return couponService.deleteCouponById(couponId);
	}

	// http://localhost:8080/coupon/updateCoupon
	@PutMapping("/updateCoupon")
	public ResponseEntity<Object> updateCoupon(@RequestParam long couponId, @RequestBody Coupon coupon) {
		return couponService.updateCoupon(couponId, coupon);
	}

	// http://localhost:8080/coupon/getCouponByType
	@GetMapping("/getCouponByType")
	public ResponseEntity<Collection<Coupon>> getCouponByType(@RequestParam CouponType type) {
		return couponService.findByType(type);
	}

	// http://localhost:8080/coupon/getCouponByPrice
	@GetMapping("/getCouponByPrice")
	public ResponseEntity<Collection<Coupon>> getCouponByPrice(@RequestParam double price) {
		return couponService.findByPrice(price);
	}

	// http://localhost:8080/coupon/getCouponByDate
	@GetMapping("/getCouponByDate")
	public ResponseEntity<Collection<Coupon>> getCouponByDate(
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
		return couponService.findByDate(date);
	}

}
