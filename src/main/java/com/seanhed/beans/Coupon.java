package com.seanhed.beans;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.cache.spi.entry.StructuredCacheEntry;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seanhed.utils.Database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

	private static String imageURL = "https://tinyurl.com/y3rauvft";
	private long id;
	private String Name;

	private Date startDate = Date.from(Database.getStartInstant());
	private Date endDate = Date.from(Database.getEndInstant());

	private int amount;
	private CouponType type;
	private String message;
	private double price;
	private String image;


	private List<Customer> customers;

	// CTOR
	public Coupon(String title, int amount, CouponType type, String message, double price, String image) {
		setName(title);
		setAmount(amount);
		setType(type);
		setMessage(message);
		setPrice(price);
		setImage(imageURL);
	}

	// new coupon with specified ID
	public Coupon(Long id, String title, int amount, CouponType type, String message, double price, String image) {
		setId(id);
		setName(title);
		setAmount(amount);
		setType(type);
		setMessage(message);
		setPrice(price);
		setImage(imageURL);
	}

	// Retrieving coupon from db
	public Coupon(Long id, String title, int amount, String type, String message, double price, String image) {
		setId(id);
		setName(title);
		setAmount(amount);
		setType(type);
		setMessage(message);
		setPrice(price);
		setImage(imageURL);
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
	 * @return the title
	 */
	@Column(unique = true)
	public String getName() {
		return Name;
	}

	/**
	 * @return the startDate
	 */
	@Column
	// @DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	@Column
	@JsonFormat(pattern = "dd-MM-yyyy")
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @return the amount
	 */
	@Column
	public int getAmount() {
		return amount;
	}

	/**
	 * @return the type
	 */
	@Column
	@Enumerated(EnumType.STRING)
	public CouponType getType() {
		return type;
	}

	/**
	 * @sets enum coupon type
	 */
	private void setType(CouponType type) {
		this.type = type;
	}

	/**
	 * @sets coupon type from string to enum
	 */
	public void setType(String type) {
		this.type = CouponType.valueOf(type);
	}

	/**
	 * @return the message
	 */
	@Column
	public String getMessage() {
		return message;
	}

	/**
	 * @return the price
	 */
	@Column
	public double getPrice() {
		return price;
	}

	/**
	 * @return the image
	 */
	@Column
	public String getImage() {
		return image;
	}

	/**
	 * @return the customers
	 */
	@ManyToMany(mappedBy = "coupons")
	public List<Customer> getCustomers() {
		return customers;
	}

	/**
	 * @return the imageURL
	 */
	public static String getImageURL() {
		return imageURL;
	}

	@Override
	public String toString() {
		return "Coupon [ ID = " + id + " | Title = " + Name + " | Start Date = " + startDate + " | End Date = "
				+ endDate + " | Amount = " + amount + " | Type = " + type + " | Message = " + message + " | Price = "
				+ price + "]";
	}




}
