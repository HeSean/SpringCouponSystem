package com.seanhed.beans;

import java.time.LocalDate;

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
public class Coupon {

	private static String imageURL = "https://tinyurl.com/y3rauvft";
	private long id;
	private String Name;
	private LocalDate startDate;
	private LocalDate endDate;
	private int amount;
	private CouponType type;
	private String message;
	private double price;
	private String image;

	// CTOR
	public Coupon(String title, LocalDate startDate, LocalDate endDate, int amount, CouponType type, String message,
			double price, String image) {
		setName(title);
		setStartDate(startDate);
		setEndDate(endDate);
		setAmount(amount);
		setType(type);
		setMessage(message);
		setPrice(price);
		setImage(imageURL);
	}

	// new coupon with specified ID
	public Coupon(Long id, String title, LocalDate startDate, LocalDate endDate, int amount, CouponType type,
			String message, double price, String image) {
		setId(id);
		setName(title);
		setStartDate(startDate);
		setEndDate(endDate);
		setAmount(amount);
		setType(type);
		setMessage(message);
		setPrice(price);
		setImage(imageURL);
	}

	// Retrieving coupon from db
	public Coupon(Long id, String title, LocalDate sDate, LocalDate eDate, int amount, String type, String message,
			double price, String image) {
		setId(id);
		setName(title);
		setStartDate(sDate);
		setEndDate(eDate);
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
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	@Column
	public LocalDate getEndDate() {
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

	@Override
	public String toString() {
		return "Coupon [ ID = " + id + " | Title = " + Name + " | Start Date = " + startDate + " | End Date = "
				+ endDate + " | Amount = " + amount + " | Type = " + type + " | Message = " + message + " | Price = "
				+ price + "]";
	}
}
