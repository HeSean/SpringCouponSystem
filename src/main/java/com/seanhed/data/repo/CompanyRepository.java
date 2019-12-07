package com.seanhed.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.seanhed.beans.Company;
@CrossOrigin(origins = "http://localhost:4200")
public interface CompanyRepository extends JpaRepository<Company, Long> {

	public List<Company> deleteByName(String name);
	
	public Company findByName(String name);
	
	

}
