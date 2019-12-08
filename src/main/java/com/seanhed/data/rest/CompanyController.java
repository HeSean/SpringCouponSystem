package com.seanhed.data.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seanhed.beans.Company;
import com.seanhed.beans.Customer;
import com.seanhed.data.service.CompanyService;

@RestController
@RequestMapping("/company")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController {

	@Autowired
	private CompanyService service;

	// http://localhost:8080/company/getCompany
	@GetMapping("/getCompany")
	public ResponseEntity<Object> getCompany(@RequestParam long companyId) {
		return service.getCompany(companyId);
	}

	// http://localhost:8080/company/getAll
	@GetMapping("getAll")
	public ResponseEntity<List<Company>> getCompnies() {
		return service.getCompanies();

	}

	// http://localhost:8080/company/addCompany
	@PostMapping("addCompany")
	public ResponseEntity<Object> addCompany(@RequestBody Company company) {
		return service.addCompany(company);
	}

	// http://localhost:8080/company/deleteCompany
	@DeleteMapping("/deleteCompany")
	public ResponseEntity<Object> deleteCompany(@RequestParam String companyName) {
		return service.deleteCompany(companyName);
	}

	// http://localhost:8080/company/updateCompany
	@PutMapping("/updateCompany")
	public ResponseEntity<Object> updateCompany(@RequestParam long companyId, @RequestBody Company company) {
		return service.updateCompany(companyId, company);
	}
}
