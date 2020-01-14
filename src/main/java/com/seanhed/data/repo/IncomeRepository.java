package com.seanhed.data.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.seanhed.beans.Income;

@CrossOrigin(origins = "http://localhost:4200")
public interface IncomeRepository extends JpaRepository<Income, Long>{

	@Query("FROM Income income WHERE name = ?1")
	public Collection<Income> findByName(String name);
}
