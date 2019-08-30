package com.seanhed.beans;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	private long id;
	private String Name;
	private String password;
	private Collection<Coupon> coupons = new LinkedHashSet<Coupon>();
	
	public Customer(long id, String custName) {
		setId(id);
		setName(custName);
	}

	public Customer(long id, String custName, String password) {
		setId(id);
		setPassword(password);
		setName(custName);
	}

	public Customer(String custName, String password) {
		setName(custName);
		setPassword(password);
	}
	
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	
	
	/**
	 * @return the custName
	 */
	@Column(unique=true)
	public String getName() {
		return Name;
	}
	
	
	/**
	 * @return the password
	 */
	@Column
	public String getPassword() {
		return password;
	}
	
	
	/**
	 * @return the coupons
	 */
	@OneToMany
	public Collection<Coupon> getCoupons() {
		return coupons;
	}
	
	/**
	 * @adds coupon to couponList
	 */
	public void addCoupon(Coupon coupon) {
		this.coupons.add(coupon);
	}

	@Override
	public String toString() {
		return "Customer [ ID = " + id + " | Customer Name = " + Name + " | Password = " + password
				+ " | Available Coupons = " + coupons + "]";
	}
}
