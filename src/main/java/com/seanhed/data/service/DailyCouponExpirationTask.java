package com.seanhed.data.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import static com.seanhed.utils.MinLog.*;

import com.seanhed.beans.Coupon;
import com.seanhed.data.repo.CouponRepository;

@Service
@Transactional
public class DailyCouponExpirationTask{

	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private CompanyServiceImpl companyService;

	@Scheduled(fixedRate = 500000)
	public void deleteExpiredCoupons() {
		info("************************ DailyTask starting to delete coupons ************************");
		try {
			Collection<Coupon> allCoupons = couponRepository.findAll();
			info("allCoupons -> " + allCoupons);
			for (Coupon coupon : allCoupons) {
				info("now working on coupon " + coupon.getName());
				info("coupons end date is - " + coupon.getEndDate() + " ****** today is - " + Calendar.getInstance().getTime());
				if (coupon.getEndDate().before(Calendar.getInstance().getTime())) {
					info("deleting " + coupon.getName());
					companyService.deleteCoupon(coupon);
				}
			}
			info("************************ DailyTask finished :D ************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
