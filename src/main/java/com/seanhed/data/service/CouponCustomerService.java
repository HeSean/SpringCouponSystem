package com.seanhed.data.service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seanhed.data.repo.CouponCustomerRepository;

@Service
@Transactional
public class CouponCustomerService {
	
	@Autowired
	CouponCustomerRepository couponCustomerRepository;
	
	@PostConstruct
	public void initDB() {
		System.out.println("initDB of CouponCustomerService");
	}
	

}
