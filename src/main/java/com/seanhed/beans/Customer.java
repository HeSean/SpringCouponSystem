package com.seanhed.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

	private Collection<Coupon> coupons = new HashSet<>();

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
	@Column(unique = true,nullable=false)
	public String getName() {
		return Name;
	}

	/**
	 * @return the password
	 */
	@Column(nullable=false)
	public String getPassword() {
		return password;
	}

	/**
	 * @return the coupons
	 */
	@JoinTable
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonBackReference
	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	@Override
	public String toString() {
		return "Customer [ ID = " + id + " | Customer Name = " + Name + " | Password = " + password
				+ " | Available Coupons = " + coupons + "]";
	}

}
