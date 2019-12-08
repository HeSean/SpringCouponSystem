package com.seanhed.data.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seanhed.beans.Company;
import com.seanhed.beans.Coupon;
import com.seanhed.beans.CouponType;
import com.seanhed.beans.Customer;
import com.seanhed.data.repo.CompanyRepository;
import com.seanhed.data.repo.CouponRepository;
import com.seanhed.data.repo.CustomerRepository;
import com.seanhed.utils.ResponseUtil;

@Service
@Transactional
public class CouponService {

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@PostConstruct
	public void initDB() {
		// couponRepository.deleteAll();
		System.out.println("initDB of CouponService");
	}

	public ResponseEntity<Object> getCoupon(long id) {
		try {
			Coupon coupon = couponRepository.getOne(id);
			return ResponseEntity.ok(coupon);
		} catch (Exception e) {
			return ResponseUtil.generateErrorCode(500, "Unable to findCoupon with id " + id);
		}
	}

	public ResponseEntity<List<Object>> getCoupons() {
		List<Coupon> coupons = couponRepository.findAll();
		System.out.println("coupons list - " + coupons);
		return ResponseUtil.generateSuccessMessageWithListBody(coupons);
	}

	public ResponseEntity<Object> addCoupon(Coupon coupon) {
		String byCompany = coupon.getMessage().substring(3);
		Company company = companyRepository.findByName(byCompany);
		System.out.println("hey ---------------------> " + coupon);
		couponRepository.save(coupon);
		System.out.println("retreived company - " + company);
		company.getCoupons().add(coupon);
		companyRepository.save(company);
		return ResponseUtil.generateSuccessMessage("added coupon");
	}

	public ResponseEntity<Object> deleteCouponByName(String name) {
		Coupon coupon = couponRepository.findByName(name);
		deleteCoupon(coupon);
		// couponRepository.deleteByName(name);
		return ResponseUtil.generateSuccessMessage("coupon " + name + " deleted :(");
	}

	public ResponseEntity<Object> deleteCouponById(long id) {
		Coupon coupon = couponRepository.findById(id);
		deleteCoupon(coupon);
		// couponRepository.deleteById(id);
		return ResponseUtil.generateSuccessMessage("coupon with id of " + id + " deleted :(");
	}

	public ResponseEntity<Object> updateCoupon(long id, Coupon newCoupon) {
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
		if (existingCoupon.getType() != newCoupon.getType() && newCoupon.getType() != null) {
			existingCoupon.setType(newCoupon.getType());
		}
		couponRepository.save(existingCoupon);
		return ResponseUtil.generateSuccessMessage("updated coupon");
	}

	public ResponseEntity<Collection<Coupon>> findByType(CouponType type) {
		try {
			Collection<Coupon> retrievedCoupons = couponRepository.findByType(type);
			System.out.println("retrievedCoupons by type = " + type + "are ---> " + retrievedCoupons);
			return ResponseEntity.ok(retrievedCoupons);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ResponseEntity<Collection<Coupon>> findByPrice(double price) {
		try {
			Collection<Coupon> retrievedCoupons = couponRepository.findByPrice(price);
			System.out.println("retrievedCoupons by price = " + price + "are ---> " + retrievedCoupons);
			return ResponseEntity.ok(retrievedCoupons);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ResponseEntity<Collection<Coupon>> findByDate(Date date) {
		try {
			Collection<Coupon> retrievedCoupons = couponRepository.findByDate(date);
			System.out.println("retrievedCoupons by date = " + date + "are ---> " + retrievedCoupons);
			return ResponseEntity.ok(retrievedCoupons);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void removeCouponFromCompanyCoupons(Coupon coupon) {
		String byCompany = coupon.getMessage().substring(3);
		Company company = companyRepository.findByName(byCompany);
		System.out.println("retreived company before delete - " + company);
		company.getCoupons().remove(coupon);
		System.out.println("retreived company after delete - " + company);
		companyRepository.save(company);
		System.out.println("removed coupon from company coupons..");
	}

	public void removeCouponFromCustomerCoupons(Coupon coupon) {
		System.out.println("coupon_customers before delete - " + coupon.getCustomers());
		List<Customer> customers = customerRepository.findByCouponsLike(coupon.getId());
		System.out.println("customer " + customers);
		for (Customer customer : customers) {
			System.out.println("retreived customer coupons before delete - " + customer);
			customer.getCoupons().remove(coupon);
			System.out.println("retreived customer coupons after delete - " + customer);
			customerRepository.save(customer);
		}
		couponRepository.save(coupon);
	}

	public void deleteCoupon(Coupon coupon) {
		try {
			System.out.println("starting to delete coupon ..");
			removeCouponFromCompanyCoupons(coupon);
			removeCouponFromCustomerCoupons(coupon);
			 couponRepository.delete(coupon.getId());
//			 couponRepository.deleteById(coupon.getId());
			 couponRepository.flush();
			System.out.println("finished deleting coupon ..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
