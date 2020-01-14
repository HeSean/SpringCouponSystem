package com.seanhed.beans;

public enum CouponType {
	Restaurants("Restaurants"), Electricity("Electricity"), Food("Food"), Health("Health"), Sports("Sports"), Camping(
			"Camping"), Travelling("Travelling");

	private String CouponType;

	private CouponType(String value) {
		this.CouponType = value;
	}
}
