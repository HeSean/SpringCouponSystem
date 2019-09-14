package com.seanhed.data.repo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.seanhed.beans.Coupon;
import com.seanhed.beans.CouponType;
@CrossOrigin(origins = "http://localhost:4200")
public interface CouponRepository extends JpaRepository<Coupon, Long> {

	public Collection<Coupon> deleteByName(String name);
	
	public Collection<Coupon> deleteById(long id);

	@Query("FROM Coupon coupon WHERE type = ?1")
	public Collection<Coupon> findByType(CouponType type) throws Exception;
	
	@Query("FROM Coupon coupon WHERE price < ?1")
	public Collection<Coupon> findByPrice(double price) throws Exception;
	
		
}
