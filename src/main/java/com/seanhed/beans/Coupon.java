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
import java.util.HashSet;
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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.cache.spi.entry.StructuredCacheEntry;
import org.springframework.context.annotation.Primary;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
	private String name;

	private Date startDate = Date.from(Database.getStartInstant());
	private Date endDate = Date.from(Database.getEndInstant());

	// private LocalDate startDate = LocalDate.of(2019, 01, 01);
	// private LocalDate endDate = LocalDate.of(2019, 12, 01);

	private int amount;
	private CouponType type;
	private String message;
	private double price;
	private String image;

	private Collection<Customer> customers = new HashSet<>();

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

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	@Column(nullable=false)
	public long getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	@Column(unique = true,nullable=false)
	public String getName() {
		return name;
	}

	/**
	 * @return the startDate
	 */
	@Column(nullable=false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	@Column(nullable=false)
	@JsonFormat(pattern = "dd-MM-yyyy")
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @return the amount
	 */
	@Column(nullable=false)
	//@JsonIgnore
	public int getAmount() {
		return amount;
	}

	/**
	 * @return the type
	 */
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	public CouponType getType() {
		return type;
	}

	/**
	 * @return the message
	 */
	@Column(nullable=false)
	public String getMessage() {
		return message;
	}

	/**
	 * @return the price
	 */
	@Column(nullable=false)
	public double getPrice() {
		return price;
	}

	/**
	 * @return the imageURL
	 */
	@Column(nullable=false)
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

	@Override
	public String toString() {
		return "Coupon [ ID = " + id + " | Title = " + name + " | Start Date = " + startDate + " | End Date = "
				+ endDate + " | Amount = " + amount + " | Type = " + type + " | Message = " + message + " | Price = "
				+ price + "]";
	}

}
