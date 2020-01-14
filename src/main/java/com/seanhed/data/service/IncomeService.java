package com.seanhed.data.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seanhed.beans.Company;
import com.seanhed.beans.Customer;
import com.seanhed.beans.Income;
import com.seanhed.data.repo.CompanyRepository;
import com.seanhed.data.repo.CustomerRepository;
import com.seanhed.data.repo.IncomeRepository;
import com.seanhed.utils.ResponseUtil;

@Service
@Transactional
public class IncomeService {

	@Autowired
	private IncomeRepository incomeRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CompanyRepository companyRepository;

	public ResponseEntity<Object> storeIncome(Income income) {
		System.out.println("storing new income - " + income);
		incomeRepository.save(income);
		return ResponseUtil.generateSuccessMessage("Income " + income.getName() + " saved.");
	}

	public ResponseEntity<Object> viewAllIncome() {
		Collection<Income> incomes = incomeRepository.findAll();
		return ResponseEntity.ok(incomes);
	}

	public ResponseEntity<Object> viewIncomeByCustomer(long customerId) {
		Customer customer = customerRepository.findById(customerId);
		Collection<Income> incomes = incomeRepository.findByName(customer.getName());
		return ResponseEntity.ok(incomes);
	}

	public ResponseEntity<Object> viewIncomeByCompany(long companyId) {
		Company company = companyRepository.findById(companyId);
		Collection<Income> incomes = incomeRepository.findByName(company.getName());
		return ResponseEntity.ok(incomes);
	}

}
