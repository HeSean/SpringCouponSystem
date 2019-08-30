package com.seanhed.data.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seanhed.beans.Company;
import com.seanhed.data.repo.CompanyRepository;

@Service
@Transactional
public class CompanyService {

	@Autowired
	private CompanyRepository repository;

	@PostConstruct
	public void initDB() {
		repository.deleteAll();
		List<Company> companies = new ArrayList<>();
		companies.add(new Company("Yesplanet", "1234", "Yesplanet@gmail.com"));
		companies.add(new Company("Hagor", "1234", "Hagor@gmail.com"));
		companies.add(new Company("Japanika", "1234", "Japanika@gmail.com"));
		repository.save(companies);
	}

	public Company getCompany(long id) {
		return repository.getOne(id);
	}

	public List<Company> getCompanies() {
		return repository.findAll();
	}

	public Company addCompany(Company company) {
		return repository.save(company);
	}

	public List<Company> deleteCompany(String name) {
		return repository.deleteByName(name);
	}

	public Company updateCompany(long id, Company company) {
		return null;
		//return repository.updateCompany(id, company);
	}

}
