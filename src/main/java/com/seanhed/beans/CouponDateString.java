package com.seanhed.beans;


import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponDateString {

	private static String imageURL = "https://tinyurl.com/y3rauvft";
	private long id;
	private String name;
	private String startDate; // = Date.from(Database.getStartInstant());
	private String endDate; // = Date.from(Database.getEndInstant());
	private int amount;
	private String type;
	private String message;
	private double price;
	private String image;
	private long companyId;

	private Collection<Customer> customers = new HashSet<>();

	public CouponDateString(String name, int amount, String type, String message, double price, String image, String startDate,
			String endDate, long companyId) {
		setName(name);
		setAmount(amount);
		setType(type);
		setMessage(message);
		setPrice(price);
		setImage(imageURL);
		setStartDate(startDate);
		setEndDate(endDate);
		setCompanyId(companyId);
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	@Column(nullable = false)
	public long getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	@Column(unique = true, nullable = false)
	public String getName() {
		return name;
	}

	/**
	 * @return the startDate
	 */
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	@Column(nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy")
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @return the amount
	 */
	@Column(nullable = false)
	// @JsonIgnore
	public int getAmount() {
		return amount;
	}

	/**
	 * @return the type
	 */
	@Column(nullable = false)
	public String getType() {
		return type;
	}

	/**
	 * @return the message
	 */
	@Column(nullable = false)
	public String getMessage() {
		return message;
	}

	/**
	 * @return the price
	 */
	@Column(nullable = false)
	public double getPrice() {
		return price;
	}

	/**
	 * @return the imageURL
	 */
	@Column(nullable = false)
	public String getImage() {
		return image;
	}

	/**
	 * @return the customers
	 */
	// @JsonBackReference(value="customers")
	@JsonIgnore
	@ManyToMany(mappedBy = "coupons")
	public Collection<Customer> getCustomers() {
		return customers;
	}

	/**
	 * @return the companyId owning the coupon
	 */
	@JsonIgnore
	@Column(nullable = false)
	public long getCompanyId() {
		return companyId;
	}

	@Override
	public String toString() {
		return "Coupon [ ID = " + id + " | Title = " + name + " | Start Date = " + startDate + " | End Date = "
				+ endDate + " | Amount = " + amount + " | Type = " + type + " | Message = " + message + " | Price = "
				+ price + "]";
	}

}
