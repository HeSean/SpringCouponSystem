package com.seanhed.data.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.seanhed.beans.ClientType;
import com.seanhed.beans.Company;
import com.seanhed.beans.Coupon;
import com.seanhed.beans.CouponDateString;
import com.seanhed.beans.CouponType;
import com.seanhed.beans.Customer;
import com.seanhed.beans.Income;
import com.seanhed.beans.IncomeType;
import com.seanhed.data.dao.CompanyServiceDAO;
import com.seanhed.data.dao.CouponClientDAO;
import com.seanhed.data.repo.CompanyRepository;
import com.seanhed.data.repo.CouponRepository;
import com.seanhed.data.repo.CustomerRepository;
import com.seanhed.utils.Database;
import com.seanhed.utils.ResponseUtil;
import static com.seanhed.utils.MinLog.*;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyServiceDAO, CouponClientDAO {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private IncomeServiceImpl incomeService;

	private Map<String, Long> tokens = new Hashtable<>();

	@PostConstruct
	public void initDB() {
		// companyRepository.deleteAll();
		// Company company1 = new Company("Yesplanet", "1234", "Yesplanet@gmail.com");
		// Company company2 = new Company("Hagor", "1234", "Hagor@gmail.com");
		// Company company3 = new Company("Japanika", "1234", "Japanika@gmail.com");
		//
		// List<Coupon> coupons = new ArrayList<>();
		// coupons.add(new Coupon("Seventh Popcorn Free", 5, CouponType.Food, "By
		// YesPlanet", 15, Database.getImageURL(),
		// Date.from(Database.getStartInstant()), Date.from(Database.getEndInstant()),
		// 1));
		// coupons.add(new Coupon("1+1 on drinks", 5, CouponType.Food, "By YesPlanet",
		// 15, Database.getImageURL(),
		// Date.from(Database.getStartInstant()), Date.from(Database.getEndInstant()),
		// 1));
		// coupons.add(new Coupon("Free Tent with Lederman swiss knife", 5,
		// CouponType.Camping, "By Hagor", 15,
		// Database.getImageURL(), Date.from(Database.getStartInstant()),
		// Date.from(Database.getEndInstant()), 4));
		// coupons.add(new Coupon("Bonus ChickenWing with takeout order", 5,
		// CouponType.Food, "By Japanika", 15,
		// Database.getImageURL(), Date.from(Database.getStartInstant()),
		// Date.from(Database.getEndInstant()), 6));
		//
		// company1.getCoupons().add(coupons.get(0));
		// company1.getCoupons().add(coupons.get(1));
		// company2.getCoupons().add(coupons.get(2));
		// company3.getCoupons().add(coupons.get(3));
		//
		// companyRepository.save(company1);
		// companyRepository.save(company2);
		// companyRepository.save(company3);
	}

	@Override
	public ResponseEntity<Object> login(String name, String password, ClientType clientType) {
		try {
			Company company = companyRepository.findByName(name);
			if (company != null) {
				if (company.getPassword().equals(password)) {
					if (!tokens.containsValue(company.getId())) {
						String token = UUID.randomUUID().toString();
						tokens.put(token, company.getId());
						info("tokens after company login -> " + tokens);
						return ResponseUtil.generateSuccessMessage(token);
					} else {
						return ResponseUtil.generateErrorCode(400, "company already logged in");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseUtil.generateErrorCode(404, "company details not found");
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

	// getCoupon
	@Override
	public ResponseEntity<Object> getCoupon(String token, long id) {
		info("tokens -> " + tokens);
		if (tokens.containsKey(token)) {
			try {
				Coupon coupon = couponRepository.getOne(id);
				return ResponseEntity.ok(coupon);
			} catch (Exception e) {
				return ResponseUtil.generateErrorCode(500, "Unable to findCoupon with id " + id);
			}
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	// getAllCoupon
	@Override
	public ResponseEntity<Object> getAllCoupons(String token) {
		info("tokens -> " + tokens);
		if (tokens.containsKey(token)) {
			Company currentCompany = companyRepository.getOne(tokens.get(token));
			return ResponseEntity.ok(currentCompany.getCoupons());
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	// createCoupon
	@Override
	public ResponseEntity<Object> createCoupon(String token, CouponDateString coupon) {
		info("tokens -> " + tokens);
		if (tokens.containsKey(token)) {
			Optional<Company> company = companyRepository.findById(tokens.get(token));
			if (company.isPresent()) {
				info("retreived company - " + company.get());
				Coupon newCoupon = new Coupon();
				newCoupon.setCompanyId(company.get().getId());
				newCoupon.setName(coupon.getName());
				newCoupon.setStartDate(Database.parseDate(coupon.getStartDate()));
				newCoupon.setEndDate(Database.parseDate(coupon.getEndDate()));
				newCoupon.setAmount(coupon.getAmount());
				newCoupon.setType(CouponType.valueOf(coupon.getType()));
				newCoupon.setMessage(coupon.getMessage());
				newCoupon.setPrice(coupon.getPrice());
				newCoupon.setImage(coupon.getImage());
				company.get().getCoupons().add(newCoupon);
				Income income = new Income();
				income.setName(company.get().getName());
				income.setDate(Calendar.getInstance().getTime());
				income.setDescription(IncomeType.COMPANY_NEW_COUPON);
				income.setAmount(100);
				incomeService.storeIncome(income);
				companyRepository.save(company.get());
			}
			return ResponseUtil.generateSuccessMessage("added coupon");
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	// updateCoupon
	@Override
	public ResponseEntity<Object> updateCoupon(String token, long couponId, CouponDateString newCoupon) {
		info("tokens -> " + tokens);
		if (tokens.containsKey(token)) {
			Coupon existingCoupon = couponRepository.getOne(couponId);
			if (!(existingCoupon.getName().equals(newCoupon.getName())) && newCoupon.getName() != null) {
				existingCoupon.setName(newCoupon.getName());
			}
			if (!(existingCoupon.getImage().equals(newCoupon.getImage())) && newCoupon.getImage() != null) {
				existingCoupon.setImage(newCoupon.getImage());
			}
			if (!(existingCoupon.getMessage().equals(newCoupon.getMessage())) && newCoupon.getMessage() != null) {
				existingCoupon.setMessage(newCoupon.getMessage());
			}
			if (newCoupon.getStartDate() != null && !newCoupon.getStartDate().isEmpty()) {
				Date newStartDate = Database.parseDate(newCoupon.getStartDate());
				if (!(existingCoupon.getStartDate().equals(newStartDate)) && newStartDate != null) {
					existingCoupon.setStartDate(newStartDate);
				}
			}
			if (newCoupon.getEndDate() != null && !newCoupon.getEndDate().isEmpty()) {
				Date newEndDate = Database.parseDate(newCoupon.getEndDate());
				if (!(existingCoupon.getEndDate().equals(newEndDate)) && newEndDate != null) {
					existingCoupon.setEndDate(newEndDate);
				}
			}
			if (existingCoupon.getAmount() != newCoupon.getAmount() && newCoupon.getAmount() > 0) {
				existingCoupon.setAmount(newCoupon.getAmount());
			}
			if (existingCoupon.getPrice() != newCoupon.getPrice() && newCoupon.getPrice() > 0) {
				existingCoupon.setPrice(newCoupon.getPrice());
			}
			if (existingCoupon.getType() != CouponType.valueOf(newCoupon.getType()) && newCoupon.getType() != null) {
				existingCoupon.setType(CouponType.valueOf(newCoupon.getType()));
			}
			Income income = new Income();
			Optional<Company> company = companyRepository.findById(tokens.get(token));
			income.setName(company.get().getName());
			income.setDate(Calendar.getInstance().getTime());
			income.setDescription(IncomeType.COMPANY_UPDATE_COUPON);
			income.setAmount(10);
			incomeService.storeIncome(income);
			couponRepository.save(existingCoupon);
			return ResponseUtil.generateSuccessMessage("updated coupon");
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	// getCouponByType
	@Override
	public ResponseEntity<Object> findByType(String token, CouponType type) {
		info("tokens -> " + tokens);
		if (tokens.containsKey(token)) {
			try {
				Company currentCompany = companyRepository.getOne(tokens.get(token));
				info("currentCompany " + currentCompany + "wanted type is " + type);
				Collection<Coupon> couponsCollection = currentCompany.getCoupons();
				info("coupons collection " + couponsCollection);
				Collection<Coupon> couponsOfWantedType = new LinkedHashSet<>();
				for (Iterator<Coupon> iterator = couponsCollection.iterator(); iterator.hasNext();) {
					Coupon coupon = iterator.next();
					info("coupon type is " + coupon.getType() + "   and the wanted type is  " + type);
					if (coupon.getType().equals(type)) {
						couponsOfWantedType.add(coupon);
					}
				}
				info("couponsOfWantedType after iteratorz " + couponsOfWantedType);
				return ResponseEntity.ok(couponsOfWantedType);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	// removeCoupon
	@Override
	public ResponseEntity<Object> deleteCouponByName(String token, String name) {
		info("tokens -> " + tokens);
		if (tokens.containsKey(token)) {
			Coupon coupon = couponRepository.findByName(name);
			deleteCoupon(coupon);
			return ResponseUtil.generateSuccessMessage("coupon " + name + " deleted :(");
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	@Override
	public ResponseEntity<Object> deleteCouponById(String token, long id) {
		info("tokens -> " + tokens);
		if (tokens.containsKey(token)) {
			Coupon coupon = couponRepository.findById(id);
			deleteCoupon(coupon);
			return ResponseUtil.generateSuccessMessage("coupon with id of " + id + " deleted :(");
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

	public void removeCouponFromCompanyCoupons(Coupon coupon) {
		Company company = companyRepository.findById(coupon.getCompanyId());
		info("retreived company before delete - " + company);
		company.getCoupons().remove(coupon);
		info("retreived company after delete - " + company);
		companyRepository.save(company);
		couponRepository.flush();
		info("removed coupon from company coupons..");
	}

	public void removeCouponFromCustomerCoupons(Coupon coupon) {
		info("coupon_customers before delete - " + coupon.getCustomers());
		List<Customer> customers = customerRepository.findByCouponsLike(coupon.getId());
		info("customer " + customers);
		for (Customer customer : customers) {
			info("retreived customer coupons before delete - " + customer);
			customer.getCoupons().remove(coupon);
			info("retreived customer coupons after delete - " + customer);
			customerRepository.save(customer);
		}
		couponRepository.save(coupon);
	}

	public void deleteCoupon(Coupon coupon) {
		try {
			info("starting to delete coupon ..");
			removeCouponFromCompanyCoupons(coupon);
			removeCouponFromCustomerCoupons(coupon);
			couponRepository.deleteById(coupon.getId());
			couponRepository.flush();
			info("finished deleting coupon ..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// getCompanyId
	@Override
	public ResponseEntity<Object> getCompanyId(String token) {
		info("tokens -> " + tokens);
		if (tokens.containsKey(token)) {
			try {
				long id = tokens.get(token);
				info("token is " + id);
				return ResponseEntity.ok(id);
			} catch (Exception e) {
				return ResponseUtil.generateErrorCode(404, "could not find a company with given ID");
			}
		} else {
			return ResponseUtil.generateErrorCode(400, "token expired");
		}
	}

}
