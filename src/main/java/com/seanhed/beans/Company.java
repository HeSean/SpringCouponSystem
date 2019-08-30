package com.seanhed.beans;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

	private long id;
	private String compName;
	private String password;
	private String email;
	private Collection<Coupon> coupons = new LinkedHashSet<Coupon>();
	
	
	//first creation ctor
	public Company(String compName, String password, String email) {
		setCompName(compName);
		setPassword(password);
		setEmail(email);
	}
	
	public Company(long id, String compName, String email) {
		setId(id);
		setCompName(compName);
		setEmail(email);
	}

	public Company(long id, String compName, String password, String email) {
		setId(id);
		setCompName(compName);
		setPassword(password);
		setEmail(email);
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
	 * @return the compName
	 */
	@Column(unique=true)
	public String getCompName() {
		return compName;
	}
	
	
	/**
	 * @return the password
	 */
	@Column
	public String getPassword() {
		return password;
	}
	
	
	/**
	 * @return the email
	 */
	@Column
	public String getEmail() {
		return email;
	}
	
	
	/**
	 * @return the coupons
	 */
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
		return "Company [ ID = " + id + " | Company Name = " + compName + " | Password = " + password
				+ " | Email Address = " + email + " | Available Coupons = " + coupons + "]";
	}
	
}
