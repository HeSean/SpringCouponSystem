package com.seanhed.data.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seanhed.beans.Coupon;
import com.seanhed.beans.CouponType;
import com.seanhed.data.repo.CouponRepository;
import com.seanhed.utils.Database;

@Service
@Transactional
public class CouponService {

	@Autowired
	private CouponRepository repository;

	@PostConstruct
	public void initDB() {
		repository.deleteAll();
		List<Coupon> coupons = new ArrayList<>();
		coupons.add(new Coupon("Seventh Popcorn Free", 5, CouponType.FOOD, "By YesPlanet", 15, Database.getImageURL()));
		coupons.add(
				new Coupon("Free Popcorn with movie", 5, CouponType.FOOD, "By YesPlanet", 15, Database.getImageURL()));
		coupons.add(new Coupon("Free Tent with Lederman swiss knife", 5, CouponType.CAMPING, "By Hagor", 15,
				Database.getImageURL()));
		coupons.add(new Coupon("Bonus ChickenWing with takeout order", 5, CouponType.FOOD, "By Japanika", 15,
				Database.getImageURL()));
		repository.save(coupons);
	}

	public Coupon getCoupon(long id) {
		return repository.getOne(id);
	}

	public List<Coupon> getCoupons() {
		return repository.findAll();
	}

	public Coupon addCoupon(Coupon coupon) {
		return this.repository.save(coupon);
	}

	public List<Coupon> deleteCoupon(String name) {
		return repository.deleteByName(name);
	}

	public Coupon updateCoupon(long id, Coupon coupon) {
		return null;
		// return repository.updateCoupon(id, coupon);
	}

}
