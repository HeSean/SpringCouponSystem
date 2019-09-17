package com.seanhed.data.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import com.seanhed.beans.Coupon;
import com.seanhed.beans.CouponType;
import com.seanhed.data.repo.CouponRepository;
import com.seanhed.data.repo.CustomerRepository;
import com.seanhed.utils.Database;

@Service
@Transactional
public class CouponService {

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@PostConstruct
	public void initDB() {
		// couponRepository.deleteAll();
		System.out.println("initDB of CouponService");
	}

	public Coupon getCoupon(long id) {
		return couponRepository.getOne(id);
	}

	public List<Coupon> getCoupons() {
		return couponRepository.findAll();
	}

	public Coupon addCoupon(Coupon coupon) {
		return couponRepository.save(coupon);
	}

	//public Collection<Coupon>
	public int deleteCouponByName(String name) {
		return couponRepository.deleteByName(name);
	}
	
	public Collection<Coupon> deleteCouponById(long id) {
		return	couponRepository.deleteById(id);
	}

	public Coupon updateCoupon(long id, Coupon newCoupon) {
		Coupon existingCoupon = couponRepository.getOne(id);
		if (!(existingCoupon.getName().equals(newCoupon.getName())) && newCoupon.getName() != null) {
			existingCoupon.setName(newCoupon.getName());
		}
		if (!(existingCoupon.getImage().equals(newCoupon.getImage())) && newCoupon.getImage() != null) {
			existingCoupon.setImage(newCoupon.getImage());
		}
		if (!(existingCoupon.getMessage().equals(newCoupon.getMessage())) && newCoupon.getMessage() != null) {
			existingCoupon.setMessage(newCoupon.getMessage());
		}
		if (!(existingCoupon.getStartDate().equals(newCoupon.getStartDate())) && newCoupon.getStartDate() != null) {
			existingCoupon.setStartDate(newCoupon.getStartDate());
		}
		if (!(existingCoupon.getEndDate().equals(newCoupon.getEndDate())) && newCoupon.getEndDate() != null) {
			existingCoupon.setEndDate(newCoupon.getEndDate());
		}
		if (existingCoupon.getAmount() != newCoupon.getAmount() && newCoupon.getAmount() > 0) {
			existingCoupon.setAmount(newCoupon.getAmount());
		}
		if (existingCoupon.getPrice() != newCoupon.getPrice() && newCoupon.getPrice() > 0) {
			existingCoupon.setPrice(newCoupon.getPrice());
		}
		return couponRepository.save(existingCoupon);
	}
	
	public  Collection<Coupon> findByType(CouponType type)  { 
		try {
			return couponRepository.findByType(type);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection<Coupon> findByPrice(double price) {
		try {
			return couponRepository.findByPrice(price);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection<Coupon> findByDate(Date date) {
		try {
			return couponRepository.findByDate(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
