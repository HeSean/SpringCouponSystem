package com.seanhed.SpringCouponSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.seanhed")
@EnableJpaRepositories("com.seanhed")
@EntityScan(basePackages = "com.seanhed")
public class SpringCouponSystemApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringCouponSystemApplication.class, args);
		System.out.println("Go!");
	}

}
