package com.seanhed.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seanhed.beans.CouponCustomer;

public interface CouponCustomerRepository extends JpaRepository<CouponCustomer, Long> {

}
