package com.seanhed.data.repo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.seanhed.beans.Coupon;
import com.seanhed.beans.CouponType;
@CrossOrigin(origins = "http://localhost:4200")
public interface CouponRepository extends JpaRepository<Coupon, Long> {

	public Collection<Coupon> deleteByName(String name);
	
	public Collection<Coupon> deleteById(long id);

	public Collection<Coupon> findByType(String type) throws Exception;


	
}
