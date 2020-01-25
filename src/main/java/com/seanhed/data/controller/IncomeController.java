package com.seanhed.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seanhed.data.service.IncomeServiceImpl;

@RestController
@RequestMapping("/income")
@CrossOrigin(origins = "http://localhost:4200")
public class IncomeController {

	@Autowired
	private IncomeServiceImpl service;

	// http://localhost:8080/income/viewAllIncome
	@GetMapping("/viewAllIncome")
	public ResponseEntity<Object> viewAllIncome() {
		return service.viewAllIncome();
	}

	// http://localhost:8080/income/viewIncomeByCustomer
	@GetMapping("/viewIncomeByCustomer")
	public ResponseEntity<Object> viewIncomeByCustomer(@RequestParam long customerId) {
		return service.viewIncomeByCustomer(customerId);
	}

	// http://localhost:8080/income/viewIncomeByCompany
	@GetMapping("/viewIncomeByCompany")
	public ResponseEntity<Object> viewIncomeByCompany(@RequestParam long companyId) {
		return service.viewIncomeByCompany(companyId);
	}

}
