package com.seanhed.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seanhed.beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	public List<Company> deleteByName(String name);

	public Company updateCompany(long id, Company company);
}
