package com.seanhed.beans;

public enum IncomeType {
	CUSTOMER_PURCHASE("Customer purchased a coupon"), COMPANY_NEW_COUPON(
			"Company created a new coupon"), COMPANY_UPDATE_COUPON("Company updated a coupon");

	public final String label;

	private IncomeType(String label) {
		this.label = label;
	}

	public String getDescription() {
		return this.label;
	}
}
