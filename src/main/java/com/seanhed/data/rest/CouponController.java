package com.seanhed.data.rest;

import java.util.Collection;
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
import com.seanhed.data.service.CouponService;

@RestController
@RequestMapping("/coupon/")
public class CouponController {

	@Autowired
	private CouponService service;

	// http://localhost:8080/coupon/getCoupon/1
	@GetMapping("/getCoupon/{id}")
	public Coupon getCoupon(@PathVariable long id) {
		return service.getCoupon(id);
	}
	
	// http://localhost:8080/coupon/getCouponByType
	@GetMapping("/getCouponByType/{type}")
	public Collection<Coupon> getCouponByType(@PathVariable String name) {
		return service.findByType(name);
	}
	

	// http://localhost:8080/coupon/getAll
	@GetMapping("/getAll")
	public List<Coupon> getCoupons() {
		return service.getCoupons();
	}

	// http://localhost:8080/coupon/add
	@PostMapping("add")
	public Coupon addCoupon(@RequestBody Coupon coupon) {
		return service.addCoupon(coupon);
	}

	// http://localhost:8080/coupon/delete/Seventh Popcorn Free
	// maybe will be @RequestBody because of spaces in name
	@DeleteMapping("/delete")
	public Collection<Coupon> deleteCoupon(@RequestBody String name) {
		return service.deleteCouponByName(name);
	}
	
	@DeleteMapping("/delete/{id}")
	public Collection<Coupon> deleteCoupon(@PathVariable long id) {
		return service.deleteCouponById(id);
	}

	// http://localhost:8080/coupon/update/1
	@PutMapping("/update/{id}")
	public Coupon updateCoupon(@PathVariable long id, @RequestBody Coupon coupon) {
		return service.updateCoupon(id, coupon);
	}

}
