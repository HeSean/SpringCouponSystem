package com.seanhed.data.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.seanhed.data.service.CompanyService;

@RestController
@RequestMapping("/company/")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController {

	@Autowired
	private CompanyService service;

	// http://localhost:8080/company/getCompany/1
	@GetMapping("/getCompany/{id}")
	public Company getCompany(@PathVariable long id) {
		return service.getCompany(id);
	}

	// http://localhost:8080/company/getAll
	@GetMapping("getAll")
	public List<Company> getCompnies() {
		return service.getCompanies();
	}

	// http://localhost:8080/company/add
	@PostMapping("add")
	public Company addCompany(@RequestBody Company company) {
		return service.addCompany(company);
	}

	// http://localhost:8080/company/delete/YesPlanet
	@DeleteMapping("/delete/{name}")
	public List<Company> deleteCompany(@PathVariable String name) {
		return service.deleteCompany(name);
	}

	// http://localhost:8080/company/update/1
	@PutMapping("/update/{id}")
	public Company updateCompany(@PathVariable long id, @RequestBody Company company) {
		return service.updateCompany(id, company);
	}
}
