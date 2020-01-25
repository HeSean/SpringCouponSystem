package com.seanhed.data.dao;

import java.util.Date;

import org.springframework.http.ResponseEntity;

public interface CustomerServiceDAO {
	public ResponseEntity<Object> buyCoupon(String token, long couponID);

	public ResponseEntity<Object> getAllAvailableCoupons(String token);

	public ResponseEntity<Object> getAllPurchasedCoupons(String token);

	public ResponseEntity<Object> findByPrice(String token, double price);

	public ResponseEntity<Object> findByDate(String token, Date date);

	public ResponseEntity<Object> deleteBoughtCoupon(long customerId, long couponId);
}
