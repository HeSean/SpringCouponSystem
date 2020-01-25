package com.seanhed.data.dao;

import org.springframework.http.ResponseEntity;

import com.seanhed.beans.CouponDateString;
import com.seanhed.beans.CouponType;

public interface CompanyServiceDAO {

	public ResponseEntity<Object> getCompanyId(String token);

	public ResponseEntity<Object> getCoupon(String token, long id);

	public ResponseEntity<Object> getAllCoupons(String token);

	public ResponseEntity<Object> createCoupon(String token, CouponDateString coupon);

	public ResponseEntity<Object> updateCoupon(String token, long couponId, CouponDateString newCoupon);

	public ResponseEntity<Object> findByType(String token, CouponType type);

	public ResponseEntity<Object> deleteCouponByName(String token, String name);

	public ResponseEntity<Object> deleteCouponById(String token, long id);

}
