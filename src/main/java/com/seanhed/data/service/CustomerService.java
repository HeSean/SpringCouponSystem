package com.seanhed.data.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seanhed.beans.Coupon;
import com.seanhed.beans.Customer;
import com.seanhed.data.repo.CouponRepository;
import com.seanhed.data.repo.CustomerRepository;
import com.seanhed.utils.ResponseUtil;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CouponRepository couponRepository;

	@PostConstruct
	public void initDB() {
		// customerRepository.deleteAll();

//		 Customer customer1 = new Customer("Sean", "1234");
//		 Customer customer2 = new Customer("Michael", "1234");
//		 Customer customer3 = new Customer("Tomer", "1234");
//		 Customer customer4 = new Customer("Aurora", "1234");
//		 Customer customer5 = new Customer("Maya", "1234");
//		
//		 customerRepository.save(customer1);
//		 customerRepository.save(customer2);
//		 customerRepository.save(customer3);
//		 customerRepository.save(customer4);
//		 customerRepository.save(customer5);

	}

	public ResponseEntity<Object> getCustomer(long id) {
		Customer customer = customerRepository.getOne(id);
		return ResponseEntity.ok(customer);
	}

	public ResponseEntity<List<Object>> getCustomers() {
		List<Customer> customers = customerRepository.findAll();
		System.out.println("customer list - " + customers);
		return ResponseUtil.generateSuccessMessageWithListBody(customers);
	}

	public ResponseEntity<Object> addCustomer(Customer customer) {
		customerRepository.save(customer);
		return ResponseUtil.generateSuccessMessage("added customer");
	}

	public ResponseEntity<Object> buyCoupon(long customerID, long couponID) {
		System.out.println("1");
		Customer customer = customerRepository.getOne(customerID);
		Coupon coupon = couponRepository.getOne(couponID);
		System.out.println("customer ---> " + customer + " , coupon ---> " + coupon);
		if (coupon.getAmount() <= 0) {
			return ResponseUtil.generateErrorCode(401, "no more coupons left to buy :( ");
		} else {
			customer.getCoupons().add(coupon);
			coupon.setAmount(coupon.getAmount() - 1);
		}
		couponRepository.save(coupon);
		customerRepository.save(customer);
		return ResponseUtil.generateSuccessMessage("Coupon Bought :) ");
	}

	public ResponseEntity<Object> deleteCustomerByName(String name) {
		customerRepository.deleteByName(name);
		return ResponseUtil.generateSuccessMessage("customer " + name + " deleted :(");

	}

	public ResponseEntity<Object> updateCustomer(long id, Customer newCustomer) {
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
		customerRepository.save(existingCustomer);
		System.out.println("updatedCustomer is - " + existingCustomer);
		return ResponseEntity.ok(existingCustomer);
	}

	public ResponseEntity<Object> deleteBoughtCoupon(long customerId, long couponId) {
		Customer customer = customerRepository.getOne(customerId);
		Coupon coupon = couponRepository.getOne(couponId);
		System.out.println("customer - " + customer + ", coupon - " + coupon);
		customer.getCoupons().remove(coupon);
		customerRepository.save(customer);
		return ResponseEntity.ok(customer);
	}

}
