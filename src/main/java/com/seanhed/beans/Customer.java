package com.seanhed.beans;

import java.util.ArrayList;
import java.util.Collection;
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
	private Collection<CouponCustomer> coupons = new ArrayList<CouponCustomer>();

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
	@Column(unique = true)
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
	
	@OneToMany(mappedBy="customer",
			fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST,
				    CascadeType.MERGE,
				    CascadeType.DETACH,
				    CascadeType.REFRESH})
	public Collection<CouponCustomer> getCoupons() {
		return coupons;
	}

	@Override
	public String toString() {
		return "Customer [ ID = " + id + " | Customer Name = " + Name + " | Password = " + password
				+ " | Available Coupons = " + coupons + "]";
	}
}
