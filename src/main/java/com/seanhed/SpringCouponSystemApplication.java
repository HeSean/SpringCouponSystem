package com.seanhed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.seanhed.data.repo.CouponRepository;
import com.seanhed.data.repo.CustomerRepository;
import static com.seanhed.utils.MinLog.*;

@SpringBootApplication(scanBasePackages = "com.seanhed")
@EnableJpaRepositories("com.seanhed")
@EntityScan(basePackages = "com.seanhed.beans")
@EnableScheduling
public class SpringCouponSystemApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringCouponSystemApplication.class, args);
		info("Go!");
		CouponRepository couponRepository = context.getBean(CouponRepository.class);
		CustomerRepository customerRepository = context.getBean(CustomerRepository.class);

		info("Coupons - " + couponRepository.findAll());
		info("Customers - " + customerRepository.findAll());
		info("******************");
	}

}
