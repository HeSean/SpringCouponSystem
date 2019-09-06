package com.seanhed.data.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seanhed.beans.Coupon;
import com.seanhed.beans.CouponCustomer;
import com.seanhed.beans.CouponCustomerId;
import com.seanhed.beans.CouponType;
import com.seanhed.beans.Customer;
import com.seanhed.data.repo.CouponCustomerRepository;
import com.seanhed.data.repo.CouponRepository;
import com.seanhed.data.repo.CustomerRepository;
import com.seanhed.utils.Database;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private CouponCustomerRepository couponCustomerRepository;

	@PostConstruct
	public void initDB() {
//		customerRepository.deleteAll();

		Customer customer1 = new Customer("Sean", "1234");
		Customer customer2 = new Customer("Michael", "1234");
		Customer customer3 = new Customer("Tomer", "1234");
		Customer customer4 = new Customer("Aurora", "1234");
		Customer customer5 = new Customer("Maya", "1234");

		List<Coupon> coupons = new ArrayList<>();
		coupons.add(new Coupon("bla", 5, CouponType.CAMPING, "By Hagor", 15, Database.getImageURL()));
		coupons.add(new Coupon("blabla", 5, CouponType.FOOD, "By Japanika", 15, Database.getImageURL()));
		coupons.add(new Coupon("blablalba", 5, CouponType.FOOD, "By YesPlanet", 15, Database.getImageURL()));

		CouponCustomer couponCustomer = new CouponCustomer();
		couponCustomer.setCoupon(coupons.get(0));
		couponCustomer.setCustomer(customer1);
		couponCustomer.setId(new CouponCustomerId(coupons.get(0).getId(), customer1.getId()));
		System.out.println("cc" + couponCustomer);
		couponCustomerRepository.save(couponCustomer);
		
		CouponCustomer couponCustomer2 = new CouponCustomer();
		couponCustomer2.setCoupon(coupons.get(1));
		couponCustomer2.setCustomer(customer1);
		couponCustomer2.setId(new CouponCustomerId(coupons.get(1).getId(), customer1.getId()));
		couponCustomerRepository.save(couponCustomer2);
		
		CouponCustomer couponCustomer3 = new CouponCustomer();
		couponCustomer3.setCoupon(coupons.get(2));
		couponCustomer3.setCustomer(customer1);
		couponCustomer3.setId(new CouponCustomerId(coupons.get(2).getId(), customer1.getId()));
		couponCustomerRepository.save(couponCustomer3);

		
		CouponCustomer couponCustomer4 = new CouponCustomer();
		couponCustomer4.setCoupon(coupons.get(2));
		couponCustomer4.setCustomer(customer2);
		couponCustomer4.setId(new CouponCustomerId(coupons.get(2).getId(), customer2.getId()));
		couponCustomerRepository.save(couponCustomer4);

		
		// customer1.getCoupons().add(coupons.get(0));
		// customer1.getCoupons().add(coupons.get(1));
		// customer1.getCoupons().add(coupons.get(2));
		// customer2.getCoupons().add(coupons.get(2));
		// customer3.getCoupons().add(coupons.get(2));
		// customer4.getCoupons().add(coupons.get(2));
		// customer5.getCoupons().add(coupons.get(2));

		customerRepository.save(customer1);
		customerRepository.save(customer2);
		customerRepository.save(customer3);
		customerRepository.save(customer4);
		customerRepository.save(customer5);
		customerRepository.flush();

	}

	public Customer getCustomer(long id) {
		return customerRepository.getOne(id);
	}

	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public String buyCoupon(Long custID, Coupon coupon) {

		return null;
	}

	public List<Customer> deleteCustomerByName(String name) {
		return customerRepository.deleteByName(name);
	}

	public Customer updateCustomer(long id, Customer newCustomer) {
		Customer existingCustomer = customerRepository.getOne(id);
		if (newCustomer.getName() != null && !(existingCustomer.getName().equals(newCustomer.getName()))) {
			existingCustomer.setName(newCustomer.getName());
		}
		if (newCustomer.getCoupons() != null && !(existingCustomer.getCoupons().equals(newCustomer.getCoupons()))) {
			existingCustomer.setCoupons(newCustomer.getCoupons());
		}
		if (newCustomer.getPassword() != null && !(existingCustomer.getPassword().equals(newCustomer.getPassword()))) {
			existingCustomer.setPassword(newCustomer.getPassword());
		}
		return customerRepository.save(existingCustomer);
	}

}
