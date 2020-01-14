package com.seanhed.data.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seanhed.beans.ClientType;
import com.seanhed.beans.Company;
import com.seanhed.beans.Customer;
import com.seanhed.data.dao.CouponClientDAO;
import com.seanhed.data.repo.CompanyRepository;
import com.seanhed.data.repo.CustomerRepository;
import com.seanhed.utils.ResponseUtil;

@Service
@Transactional
public class AdminService implements CouponClientDAO {
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CustomerRepository customerRepository;

	private Map<String, Long> tokens = new Hashtable<>();

	@Override
	public ResponseEntity<Object> login(String name, String password, ClientType clientType) {
		if (name.equalsIgnoreCase("admin")) {
			if (password.equals("1234")) {
				String token = UUID.randomUUID().toString();
				tokens.put(token, new Random().nextLong());
				System.out.println("tokens after admin login -> " + tokens);
				return ResponseUtil.generateSuccessMessage(token);
			}
		}
		return ResponseUtil.generateErrorCode(400, "name or password are incorrect");
	}

	@Override
	public ResponseEntity<Object> logout(String token) {
		if (tokens.containsKey(token)) {
			tokens.remove(token);
			return ResponseUtil.generateSuccessMessage("Logged out");
		} else {
			return ResponseUtil.generateErrorCode(400, "failed to log out");
		}
	}

	// getcompany
	public ResponseEntity<Object> getCompany(String token, long id) {
		System.out.println("tokens -> " + tokens);
		if (tokens.containsKey(token)) {
			try {
				if (id > 0) {
					Company company = companyRepository.getOne(id);
					System.out.println("retrevied company -> " + company);
					return ResponseEntity.ok(company);
				} else {
					return ResponseUtil.generateErrorCode(400, "id must be a positive number");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return ResponseUtil.generateErrorCode(404, "could not find a company with given ID");
			}
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	// getAllCompanies
	public ResponseEntity<Object> getCompanies(String token) {
		if (tokens.containsKey(token)) {
			try {
				List<Company> companies = companyRepository.findAll();
				if (companies != null) {
					return ResponseEntity.ok(companies);
				}
			} catch (Exception e) {
				return ResponseUtil.generateErrorCode(404, "no companies found in database");
			}
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
		return null;
	}

	// createCompany
	public ResponseEntity<Object> createCompany(String token, Company company) {
		System.out.println("createCompany -------> " + company);
		if (tokens.containsKey(token)) {
			if (!company.getName().isEmpty() && !company.getEmail().isEmpty() && !company.getPassword().isEmpty()) {
				companyRepository.save(company);
				return ResponseUtil.generateSuccessMessage("company " + company.getName() + " added :)");
			} else {
				return ResponseUtil.generateErrorCode(400, "company's name, email or password must not be empty");
			}
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	// removeCompany
	public ResponseEntity<Object> deleteCompany(String token, String name) {
		if (tokens.containsKey(token)) {
			try {
				companyRepository.deleteByName(name);
				return ResponseUtil.generateSuccessMessage("company " + name + " deleted :(");
			} catch (Exception e) {
				return ResponseUtil.generateErrorCode(404, "no company in database with given name - " + name);
			}
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	// updateCompany
	public ResponseEntity<Object> updateCompany(String token, long id, Company newCompany) {
		if (tokens.containsKey(token)) {
			Company existingCompany = companyRepository.getOne(id);
			if (newCompany.getName() != null && !(existingCompany.getName().equals(newCompany.getName()))) {
				existingCompany.setName(newCompany.getName());
			}
			if (newCompany.getPassword() != null && !(existingCompany.getPassword().equals(newCompany.getPassword()))) {
				existingCompany.setPassword(newCompany.getPassword());
			}
			if (newCompany.getEmail() != null && !(existingCompany.getEmail().equals(newCompany.getEmail()))) {
				existingCompany.setEmail(newCompany.getEmail());
			}
//			if (newCompany.getCoupons() != null && !(existingCompany.getCoupons().equals(newCompany.getCoupons()))) {
//				existingCompany.setCoupons(newCompany.getCoupons());
//			}
			try {
				companyRepository.save(existingCompany);
				System.out.println("updated company is - " + existingCompany);
				return ResponseEntity.ok(existingCompany);
			} catch (Exception e) {
				return ResponseUtil.generateErrorCode(500, "failed to update company - " + e.getLocalizedMessage());
			}
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	// getCustomer
	public ResponseEntity<Object> getCustomer(String token, long id) {
		if (tokens.containsKey(token)) {
			Customer customer = customerRepository.getOne(id);
			return ResponseEntity.ok(customer);
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	// getAllCustomer
	public ResponseEntity<Object> getCustomers(String token) {
		if (tokens.containsKey(token)) {
			List<Customer> customers = customerRepository.findAll();
			System.out.println("customer list - " + customers);
			return ResponseEntity.ok(customers);
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	// createCustomer
	public ResponseEntity<Object> createCustomer(String token, Customer customer) {
		if (tokens.containsKey(token)) {
			System.out.println("customer details are - "  + customer);
			customerRepository.save(customer);
			return ResponseUtil.generateSuccessMessage("added customer");
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	// removeCustomer
	public ResponseEntity<Object> deleteCustomerByName(String token, String name) {
		if (tokens.containsKey(token)) {
			customerRepository.deleteByName(name);
			return ResponseUtil.generateSuccessMessage("customer " + name + " deleted :(");
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}

	}

	// updateCustomer
	public ResponseEntity<Object> updateCustomer(String token, long id, Customer newCustomer) {
		if (tokens.containsKey(token)) {
			Customer existingCustomer = customerRepository.getOne(id);
			if (newCustomer.getName() != null && !(existingCustomer.getName().equals(newCustomer.getName()))) {
				existingCustomer.setName(newCustomer.getName());
			}
			if (newCustomer.getCoupons() != null && !(existingCustomer.getCoupons().equals(newCustomer.getCoupons()))) {
				existingCustomer.setCoupons(newCustomer.getCoupons());
			}
			if (newCustomer.getPassword() != null
					&& !(existingCustomer.getPassword().equals(newCustomer.getPassword()))) {
				existingCustomer.setPassword(newCustomer.getPassword());
			}
			customerRepository.save(existingCustomer);
			System.out.println("updatedCustomer is - " + existingCustomer);
			return ResponseEntity.ok(existingCustomer);
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}

	}

}
