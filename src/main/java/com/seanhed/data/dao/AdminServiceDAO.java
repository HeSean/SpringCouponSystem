package com.seanhed.data.dao;

import org.springframework.http.ResponseEntity;

import com.seanhed.beans.Company;
import com.seanhed.beans.Customer;

public interface AdminServiceDAO {
	public ResponseEntity<Object> getCompany(String token, long id);

	public ResponseEntity<Object> getCompanies(String token);

	public ResponseEntity<Object> createCompany(String token, Company company);

	public ResponseEntity<Object> deleteCompany(String token, String name);

	public ResponseEntity<Object> updateCompany(String token, long id, Company newCompany);

	public ResponseEntity<Object> getCustomer(String token, long id);

	public ResponseEntity<Object> getCustomers(String token);

	public ResponseEntity<Object> createCustomer(String token, Customer customer);

	public ResponseEntity<Object> deleteCustomerByName(String token, String name);

	public ResponseEntity<Object> updateCustomer(String token, long id, Customer newCustomer);

}
