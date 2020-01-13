package com.seanhed.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seanhed.beans.Company;
import com.seanhed.beans.Customer;
import com.seanhed.data.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
	
	@Autowired
	private AdminService service;

	// http://localhost:8080/admin/getCompany
	@GetMapping("/getCompany")
	public ResponseEntity<Object> getCompany(@RequestParam String token, @RequestParam long companyId) {
		return service.getCompany(token,companyId);
	}

	// http://localhost:8080/admin/getAllCompanies
	@GetMapping("getAllCompanies")
	public ResponseEntity<Object> getCompnies(@RequestParam String token) {
		return service.getCompanies(token);

	}

	// http://localhost:8080/admin/createCompany
	@PostMapping("createCompany")
	public ResponseEntity<Object> createCompany(@RequestParam String token, @RequestBody Company company) {
		return service.createCompany(token,company);
	}

	// http://localhost:8080/admin/deleteCompany
	@DeleteMapping("/deleteCompany")
	public ResponseEntity<Object> deleteCompany(@RequestParam String token, @RequestParam String companyName) {
		return service.deleteCompany(token,companyName);
	}

	// http://localhost:8080/admin/updateCompany
	@PutMapping("/updateCompany")
	public ResponseEntity<Object> updateCompany(@RequestParam String token, @RequestParam long companyId, @RequestBody Company company) {
		return service.updateCompany(token,companyId, company);
	}

	// http://localhost:8080/admin/getCustomer
	@GetMapping("/getCustomer")
	public ResponseEntity<Object> getCustomer(@RequestParam String token, @RequestParam long customerId) {
		return service.getCustomer(token,customerId);
	}

	// http://localhost:8080/admin/getAll
	@GetMapping("getAllCustomers")
	public ResponseEntity<Object> getCustomers(@RequestParam String token) {
		return service.getCustomers(token);
	}

	// http://localhost:8080/admin/createCustomer
	@PostMapping("createCustomer")
	public ResponseEntity<Object> createCustomer(@RequestParam String token, @RequestBody Customer customer) {
		return service.createCustomer(token,customer);
	}

	// http://localhost:8080/admin/deleteCustomer
	@DeleteMapping("/deleteCustomer")
	public ResponseEntity<Object> deleteCustomer(@RequestParam String token, @RequestParam String customerName) {
		return service.deleteCustomerByName(token,customerName);
	}

	// http://localhost:8080/admin/updateCustomer
	@PutMapping("/updateCustomer")
	public ResponseEntity<Object> updateCustomer(@RequestParam String token, @RequestParam long customerId, @RequestBody Customer customer) {
		return service.updateCustomer(token,customerId, customer);
	}
}
