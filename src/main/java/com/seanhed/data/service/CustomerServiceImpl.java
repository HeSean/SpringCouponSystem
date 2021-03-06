package com.seanhed.data.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seanhed.beans.ClientType;
import com.seanhed.beans.Company;
import com.seanhed.beans.Coupon;
import com.seanhed.beans.Customer;
import com.seanhed.beans.Income;
import com.seanhed.beans.IncomeType;
import com.seanhed.data.dao.CouponClientDAO;
import com.seanhed.data.dao.CustomerServiceDAO;
import com.seanhed.data.repo.CouponRepository;
import com.seanhed.data.repo.CustomerRepository;
import com.seanhed.utils.ResponseUtil;
import static com.seanhed.utils.MinLog.*;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerServiceDAO, CouponClientDAO {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private IncomeServiceImpl incomeService;

	private Map<String, Long> tokens = new Hashtable<>();

	@PostConstruct
	public void initDB() {
		// customerRepository.deleteAll();

		// Customer customer1 = new Customer("Sean", "1234");
		// Customer customer2 = new Customer("Michael", "1234");
		// Customer customer3 = new Customer("Tomer", "1234");
		// Customer customer4 = new Customer("Aurora", "1234");
		// Customer customer5 = new Customer("Maya", "1234");
		//
		// customerRepository.save(customer1);
		// customerRepository.save(customer2);
		// customerRepository.save(customer3);
		// customerRepository.save(customer4);
		// customerRepository.save(customer5);
	}

	@Override
	public ResponseEntity<Object> login(String name, String password, ClientType clientType) {
		try {
			Customer customer = customerRepository.findByName(name);
			if (customer != null) {
				if (customer.getPassword().equals(password)) {
					if (!tokens.containsValue(customer.getId())) {
						String token = UUID.randomUUID().toString();
						tokens.put(token, customer.getId());
						info("tokens after customer login -> " + tokens);
						return ResponseUtil.generateSuccessMessage(token);
					} else {
						return ResponseUtil.generateErrorCode(400, "customer already logged in");
					}
				}
			} else {
				return ResponseUtil.generateErrorCode(404, "customer details not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseEntity<Object> logout(String token) {
		if (tokens.containsKey(token)) {
			tokens.remove(token);
			return ResponseUtil.generateSuccessMessage("Logged out");
		} else {
			return ResponseUtil.generateErrorCode(400, "failed to log out");
		}
	}

	// purchaseCoupon
	@Override
	public ResponseEntity<Object> buyCoupon(String token, long couponID) {
		info("tokens -> " + tokens);
		if (tokens.containsKey(token)) {
			info("1");
			Customer customer = customerRepository.getOne(tokens.get(token));
			Coupon coupon = couponRepository.getOne(couponID);
			info("customer ---> " + customer + " , coupon ---> " + coupon);
			if (coupon.getAmount() <= 0) {
				return ResponseUtil.generateErrorCode(401, "no more coupons left to buy :( ");
			} else {
				customer.getCoupons().add(coupon);
				coupon.setAmount(coupon.getAmount() - 1);
				Income income = new Income();
				income.setName(customer.getName());
				income.setAmount(coupon.getPrice());
				income.setDate(Calendar.getInstance().getTime());
				income.setDescription(IncomeType.CUSTOMER_PURCHASE);
				incomeService.storeIncome(income);
			}
			couponRepository.save(coupon);
			customerRepository.save(customer);
			return ResponseUtil.generateSuccessMessage("Coupon Bought :) ");
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	// getAllAvailableCoupons
	@Override
	public ResponseEntity<Object> getAllAvailableCoupons(String token) {
		info("tokens -> " + tokens);
		if (tokens.containsKey(token)) {
			Customer customer = customerRepository.getOne(tokens.get(token));
			Collection<Coupon> allCoupons = couponRepository.findAll();
			Collection<Coupon> purchasedCoupons = customer.getCoupons();
			info("all coupons before cleaning of purchased - " + allCoupons);
			for (Coupon coupon : purchasedCoupons) {
				if (allCoupons.contains(coupon)) {
					allCoupons.remove(coupon);
				}
			}
			info("all coupons after cleaning of purchased - " + allCoupons);
			return ResponseEntity.ok(allCoupons);
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	// getAllPurchasedCoupons
	@Override
	public ResponseEntity<Object> getAllPurchasedCoupons(String token) {
		info("tokens -> " + tokens);
		if (tokens.containsKey(token)) {
			Customer customer = customerRepository.getOne(tokens.get(token));
			Collection<Coupon> purchasedCoupons = customer.getCoupons();
			return ResponseEntity.ok(purchasedCoupons);
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	// getAllPurchasedByPrice
	@Override
	public ResponseEntity<Object> findByPrice(String token, double price) {
		info("tokens -> " + tokens);
		if (tokens.containsKey(token)) {
			try {
				info("customerId is -> " + tokens.get(token));
				Customer customer = customerRepository.getOne(tokens.get(token));
				Collection<Coupon> retrievedCoupons = customer.getCoupons();
				info("retrievedCoupons are ---> " + retrievedCoupons);
				ArrayList<Coupon> coupons = new ArrayList<>();
				for (Coupon coupon : retrievedCoupons) {
					if (retrievedCoupons.iterator().hasNext() && coupon.getPrice() <= price) {
						coupons.add(coupon);
					}
				}
				Collections.sort(coupons, new Comparator<Coupon>() {
					public int compare(Coupon c1, Coupon c2) {
						return Double.compare(c1.getPrice(), c2.getPrice());
					}
				});
				info("coupons after sort -> " + coupons);
				return ResponseEntity.ok(coupons);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	// getAllPurchasedByDate
	@Override
	public ResponseEntity<Object> findByDate(String token, Date date) {
		info("tokens -> " + tokens);
		if (tokens.containsKey(token)) {
			try {
				Customer customer = customerRepository.getOne(tokens.get(token));
				Collection<Coupon> retrievedCoupons = customer.getCoupons();
				Collection<Coupon> copy = new LinkedList<>();
				for (Coupon coupon : retrievedCoupons) {
					copy.add(coupon);
				}
				info("coupons before removeIf -> " + copy);
				copy.removeIf(coupon -> coupon.getEndDate().before(date));
				info("coupons after removeIf -> " + copy);
				return ResponseEntity.ok(copy);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	// deleteBoughtCoupon
	@Override
	public ResponseEntity<Object> deleteBoughtCoupon(long customerId, long couponId) {
		Customer customer = customerRepository.getOne(customerId);
		Coupon coupon = couponRepository.getOne(couponId);
		info("customer - " + customer + ", coupon - " + coupon);
		customer.getCoupons().remove(coupon);
		customerRepository.save(customer);
		return ResponseEntity.ok(customer);
	}
}
