package com.seanhed.data.rest;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seanhed.beans.Company;
import com.seanhed.beans.Coupon;
import com.seanhed.beans.CouponType;
import com.seanhed.beans.Customer;
import com.seanhed.data.service.CompanyService;
import com.seanhed.data.service.CouponService;
import com.seanhed.data.service.CustomerService;

import io.swagger.models.Response;
import org.json.JSONObject;

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
	public ResponseEntity<?> getCoupon(@PathVariable long id) {
		Coupon coupon = couponService.getCoupon(id);
		System.out.println("getCouponResponse - " + coupon);
		return ResponseEntity.ok().body(coupon);
	}

	// http://localhost:8080/coupon/getAll
	@GetMapping("/getAll")
	public ResponseEntity<List<Coupon>> getCoupons() {
		List<Coupon> coupons = couponService.getCoupons();
		// if (!coupons.isEmpty()) {
		return ResponseEntity.ok().body(coupons);
	}

	// http://localhost:8080/coupon/add
	@PostMapping("add")
//	@RequestMapping(path="add",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon) {
		Coupon savedCoupon = couponService.addCoupon(coupon);
		return ResponseEntity.ok().body(savedCoupon);
	}

	// http://localhost:8080/coupon/delete/Seventh Popcorn Free
	// maybe will be @RequestBody because of spaces in name
	@DeleteMapping("/delete")
	//public ResponseEntity<Collection<Coupon>> 
	public ResponseEntity<?> deleteCouponByName(@RequestBody String name) {
		//Collection<Coupon> deletedCoupons = 
		int deletedCoupons = couponService.deleteCouponByName(name);
		return ResponseEntity.ok().body(deletedCoupons);
	}

	@DeleteMapping("/delete/{companyId}/{couponId}")
	public ResponseEntity<Company> deleteCouponById(@PathVariable long companyId, @PathVariable long couponId) {
		Company company = companyService.deleteCreatedCoupon(companyId, couponId);
		return ResponseEntity.ok().body(company);
	}

	// http://localhost:8080/coupon/update/1
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCoupon(@PathVariable long id, @RequestBody Coupon coupon) {
		Coupon updatedCoupon = couponService.updateCoupon(id, coupon);
		return ResponseEntity.ok().body(updatedCoupon);
	}

	// http://localhost:8080/coupon/getCouponByType
	@GetMapping("/getCouponByType/{type}")
	public ResponseEntity<Collection<Coupon>> getCouponByType(@PathVariable CouponType type) {
		Collection<Coupon> retrievedCoupons = couponService.findByType(type);
		return ResponseEntity.ok().body(retrievedCoupons);
	}

	// http://localhost:8080/coupon/getCouponByPrice
	@GetMapping("/getCouponByPrice/{price}")
	public ResponseEntity<Collection<Coupon>> getCouponByPrice(@PathVariable double price) {
		Collection<Coupon> retrievedCoupons = couponService.findByPrice(price);
		return ResponseEntity.ok().body(retrievedCoupons);
	}

	// http://localhost:8080/coupon/getCouponByDate
	@GetMapping("/getCouponByDate")
	public ResponseEntity<Collection<Coupon>> getCouponByDate(
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
		Collection<Coupon> retrievedCoupons = couponService.findByDate(date);
		System.out.println("retrievedCoupons by date = " + date + "are ---> " + retrievedCoupons);
		return ResponseEntity.ok().body(retrievedCoupons);
	}

}
