package com.seanhed.data.dao;

import org.springframework.http.ResponseEntity;

import com.seanhed.beans.Income;

public interface IncomeServiceDAO {
	public ResponseEntity<Object> storeIncome(Income income);
	
	public ResponseEntity<Object> viewAllIncome();
	
	public ResponseEntity<Object> viewIncomeByCustomer(long customerId);
	
	public ResponseEntity<Object> viewIncomeByCompany(long companyId);
}
