package com.seanhed.SpringCouponSystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.seanhed.beans.Coupon;
import com.seanhed.beans.CouponType;
import com.seanhed.beans.Customer;
import com.seanhed.data.repo.CompanyRepository;
import com.seanhed.data.repo.CouponRepository;
import com.seanhed.data.repo.CustomerRepository;
import com.seanhed.data.service.CustomerService;
import com.seanhed.utils.Database;

@SpringBootApplication(scanBasePackages = "com.seanhed")
@EnableJpaRepositories("com.seanhed")
@EntityScan(basePackages = "com.seanhed")
public class SpringCouponSystemApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringCouponSystemApplication.class, args);
		System.out.println("Go!");
		CouponRepository couponRepository = context.getBean(CouponRepository.class);
		CustomerRepository customerRepository = context.getBean(CustomerRepository.class);
		CustomerService customerService = context.getBean(CustomerService.class);

		List<Coupon> coupons = couponRepository.findAll();
		List<Customer> customers = customerRepository.findAll();

		System.out.println("Coupons - " + coupons);
		System.out.println("customers - " + customers);
		
//		System.out.println(customerService.buyCoupon(customers.get(0).getId(),coupons.get(0).getId()));
//		System.out.println(customerService.buyCoupon(customers.get(1).getId(),coupons.get(0).getId()));
//		System.out.println(customerService.buyCoupon(customers.get(2).getId(),coupons.get(0).getId()));
//		System.out.println(customerService.buyCoupon(customers.get(0).getId(),coupons.get(0).getId()));
//		System.out.println(customerService.buyCoupon(customers.get(1).getId(),coupons.get(0).getId()));
//		System.out.println(customerService.buyCoupon(customers.get(2).getId(),coupons.get(0).getId()));


	}

}
