package com.seanhed.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Income {
	
	private long id;
	private String name;
	private Date date;
	private IncomeType description;
	private double amount;
	
	
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
	 * @return the name
	 */
	@Column(nullable = false)
	public String getName() {
		return name;
	}
	
	/**
	 * @return the date
	 */
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	public Date getDate() {
		return date;
	}
	
	/**
	 * @return the description
	 */
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	public IncomeType getDescription() {
		return description;
	}
	
	/**
	 * @return the amount
	 */
	@Column(nullable = false)
	public double getAmount() {
		return amount;
	}
	

}
