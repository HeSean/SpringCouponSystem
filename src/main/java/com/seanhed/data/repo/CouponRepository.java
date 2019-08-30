package com.seanhed.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seanhed.beans.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	public List<Coupon> deleteByName(String name);

	//public Coupon updateCoupon(long id, Coupon coupon);
}
