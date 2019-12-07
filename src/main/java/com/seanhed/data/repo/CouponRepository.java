package com.seanhed.data.repo;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.seanhed.beans.Coupon;
import com.seanhed.beans.CouponType;

@CrossOrigin(origins = "http://localhost:4200")
public interface CouponRepository extends JpaRepository<Coupon, Long> {

	public Collection<Coupon> deleteById(long id);

	@Transactional
	@Modifying
	@Query("DELETE FROM Coupon coupon WHERE name = ?1")
	public int deleteByName(String name);

	@Query("FROM Coupon coupon WHERE type = ?1")
	public Collection<Coupon> findByType(CouponType type);

	@Query("FROM Coupon coupon WHERE price < ?1")
	public Collection<Coupon> findByPrice(double price);

	@Query("FROM Coupon coupon WHERE end_date < ?1")
	public Collection<Coupon> findByDate(Date date);
	
	@Query("FROM Coupon coupon WHERE id = ?1")
	public Coupon findById(long id);
	
	@Query("FROM Coupon coupon WHERE name = ?1")
	public Coupon findByName(String name);

}
