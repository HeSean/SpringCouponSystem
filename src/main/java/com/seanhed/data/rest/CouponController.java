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

	// http://localhost:8080/coupon/getCoupon/1
	@GetMapping("/getCoupon/{id}")
	public ResponseEntity<Object> getCoupon(@PathVariable long id) {
		return couponService.getCoupon(id);
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
		// ResponseEntity.ok().body(savedCoupon);
		return couponService.addCoupon(coupon);
	}

	// http://localhost:8080/coupon/deleteCoupon
	@DeleteMapping("/deleteCoupon")
	public ResponseEntity<Object> deleteCouponByName(
			@RequestBody String name
			//@RequestParam String name
			) {
		return couponService.deleteCouponByName(name);
	}

	// http://localhost:8080/coupon/deleteCouponById/{companyId}/{couponId}
	@DeleteMapping("/deleteCouponById/{companyId}/{couponId}")
	public ResponseEntity<Object> deleteCouponById(@PathVariable long companyId, @PathVariable long couponId) {
		return companyService.deleteCreatedCoupon(companyId, couponId);
	}

	// http://localhost:8080/coupon/updateCoupon/1
	@PutMapping("/updateCoupon/{id}")
	public ResponseEntity<Object> updateCoupon(@PathVariable long id, @RequestBody Coupon coupon) {
		return couponService.updateCoupon(id, coupon);
	}

	// http://localhost:8080/coupon/getCouponByType
	@GetMapping("/getCouponByType/{type}")
	public ResponseEntity<Collection<Coupon>> getCouponByType(@PathVariable CouponType type) {
		return couponService.findByType(type);
	}

	// http://localhost:8080/coupon/getCouponByPrice
	@GetMapping("/getCouponByPrice/{price}")
	public ResponseEntity<Collection<Coupon>> getCouponByPrice(@PathVariable double price) {
		return couponService.findByPrice(price);
	}

	// http://localhost:8080/coupon/getCouponByDate
	@GetMapping("/getCouponByDate")
	public ResponseEntity<Collection<Coupon>> getCouponByDate(
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
		return couponService.findByDate(date);
	}

}
