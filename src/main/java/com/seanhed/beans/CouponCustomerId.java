package com.seanhed.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponCustomerId implements Serializable {
	
	private long couponId;
	private long customerId;
	
	
	/**
	 * @return the couponId
	 */
	@Column
	public long getCouponId() {
		return couponId;
	}
	/**
	 * @return the customerId
	 */
	@Column
	public long getCustomerId() {
		return customerId;
	}
	

}
