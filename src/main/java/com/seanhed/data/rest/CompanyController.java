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
import org.springframework.web.bind.annotation.RestController;

import com.seanhed.beans.Company;
import com.seanhed.beans.Customer;
import com.seanhed.data.service.CompanyService;

@RestController
@RequestMapping("/company/")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController {

	@Autowired
	private CompanyService service;

	// http://localhost:8080/company/getCompany/1
	@GetMapping("/getCompany/{id}")
	public ResponseEntity<?> getCompany(@PathVariable long id) {
		Company company =  service.getCompany(id);
		System.out.println("returned company is - " + company);
		return ResponseEntity.ok().body(company);
	}

	// http://localhost:8080/company/getAll
	@GetMapping("getAll")
	public ResponseEntity<List<Company>> getCompnies() {
		List<Company>companies = service.getCompanies();
		System.out.println("retrived companies - " + companies);
		return ResponseEntity.ok().body(companies);
	}

	// http://localhost:8080/company/add
	@PostMapping("add")
	public ResponseEntity<?> addCompany(@RequestBody Company company) {
		Company newCompany =  service.addCompany(company);
		System.out.println("newCompany is - " + newCompany);
		return ResponseEntity.ok().body(newCompany);
	}

	// http://localhost:8080/company/delete/YesPlanet
	@DeleteMapping("/delete/{name}")
	public  ResponseEntity<List<Company>> deleteCompany(@PathVariable String name) {
		List<Company> companies = service.deleteCompany(name);
		System.out.println("deleted companies - " + companies);
		return ResponseEntity.ok().body(companies);
	}

	// http://localhost:8080/company/update/1
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCompany(@PathVariable long id, @RequestBody Company company) {
		Company newCompany =  service.updateCompany(id, company);
		System.out.println("newCompany is - " + newCompany);
		return ResponseEntity.ok().body(newCompany); 
	}
}
