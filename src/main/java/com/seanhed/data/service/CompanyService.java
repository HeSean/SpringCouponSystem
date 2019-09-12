package com.seanhed.data.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seanhed.beans.Company;
import com.seanhed.beans.Coupon;
import com.seanhed.beans.CouponType;
import com.seanhed.data.repo.CompanyRepository;
import com.seanhed.utils.Database;

@Service
@Transactional
public class CompanyService {

	@Autowired
	private CompanyRepository repository;

	@PostConstruct
	public void initDB() {
		//repository.deleteAll();
		Company company1 = new Company("Yesplanet", "1234", "Yesplanet@gmail.com");
		Company company2 = new Company("Hagor", "1234", "Hagor@gmail.com");
		Company company3 = new Company("Japanika", "1234", "Japanika@gmail.com");

		List<Coupon> coupons = new ArrayList<>();
		coupons.add(new Coupon("Seventh Popcorn Free", 5, CouponType.FOOD, "By YesPlanet", 15, Database.getImageURL()));
		coupons.add(new Coupon("1+1 on drinks", 5, CouponType.FOOD, "By YesPlanet", 15, Database.getImageURL()));
		coupons.add(new Coupon("Free Tent with Lederman swiss knife", 5, CouponType.CAMPING, "By Hagor", 15,
				Database.getImageURL()));
		coupons.add(new Coupon("Bonus ChickenWing with takeout order", 5, CouponType.FOOD, "By Japanika", 15,
				Database.getImageURL()));

		company1.getCoupons().add(coupons.get(0));
		company1.getCoupons().add(coupons.get(1));
		company2.getCoupons().add(coupons.get(2));
		company3.getCoupons().add(coupons.get(3));

		repository.save(company1);
		repository.save(company2);
		repository.save(company3);

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

	public Company updateCompany(long id, Company newCompany) {
		Company existingCompany = repository.getOne(id);
		if (newCompany.getName() != null && !(existingCompany.getName().equals(newCompany.getName()))) {
			existingCompany.setName(newCompany.getName());
		}
		if (newCompany.getPassword() != null && !(existingCompany.getPassword().equals(newCompany.getPassword())))  {
			existingCompany.setPassword(newCompany.getPassword());
		}
		if (newCompany.getEmail() != null && !(existingCompany.getEmail().equals(newCompany.getEmail()))) {
			existingCompany.setEmail(newCompany.getEmail());
		}
		if (newCompany.getCoupons() != null && !(existingCompany.getCoupons().equals(newCompany.getCoupons()))) {
			existingCompany.setCoupons(newCompany.getCoupons());
		}
		return repository.save(existingCompany);
	}

}
