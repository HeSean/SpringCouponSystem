package com.seanhed.beans;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponCustomer {
	
	private CouponCustomerId id;
	private Coupon coupon;
	private Customer customer;
	
	/**
	 * @return the id
	 */
	@EmbeddedId
	public CouponCustomerId getId() {
		return id;
	}
	
	/**
	 * @return the coupon
	 */
	@ManyToOne(fetch= FetchType.LAZY, cascade=CascadeType.ALL)
	@MapsId("couponId")
	public Coupon getCoupon() {
		return coupon;
	}
	
	/**
	 * @return the customer
	 */
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@MapsId("customerId")
	public Customer getCustomer() {
		return customer;
	}
}
