package com.seanhed.beans;

public enum CouponType {
	Restaurants("Restaurants"), Electricity("Electricity"), Food("Food"), Health("Health"), Sports("Sports"), Camping(
			"Camping"), Travelling("Travelling");

	private final String CouponType;

	private CouponType(String label) {
		this.CouponType = label;
	}
}
