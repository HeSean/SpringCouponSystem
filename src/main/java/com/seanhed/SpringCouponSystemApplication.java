package com.seanhed;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

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
@EntityScan(basePackages = "com.seanhed.beans")
@EnableScheduling
public class SpringCouponSystemApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringCouponSystemApplication.class, args);
		System.out.println("Go!");
		CouponRepository couponRepository = context.getBean(CouponRepository.class);
		CustomerRepository customerRepository = context.getBean(CustomerRepository.class);

		List<Coupon> coupons = couponRepository.findAll();
		List<Customer> customers = customerRepository.findAll();

		// LocalDate localDate = LocalDate.of(2019, 01, 01);
		// System.out.println("localDate " + localDate);

		System.out.println("Coupons - " + coupons);
		System.out.println("customers - " + customers);
		System.out.println("******************");
	}

}