package com.CouponSystem.springboot.myCouponSystem.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CouponSystem.springboot.myCouponSystem.entities.Coupon;
import com.CouponSystem.springboot.myCouponSystem.entities.Income;
import com.CouponSystem.springboot.myCouponSystem.enums.CouponCategoryType;
import com.CouponSystem.springboot.myCouponSystem.enums.ErrorType;
import com.CouponSystem.springboot.myCouponSystem.enums.IncomeDescription;
import com.CouponSystem.springboot.myCouponSystem.exceptions.ApplicationException;
import com.CouponSystem.springboot.myCouponSystem.repositories.CompanyRepository;
import com.CouponSystem.springboot.myCouponSystem.repositories.CouponRepository;
import com.CouponSystem.springboot.myCouponSystem.utils.JpaUtils;

@Service
public class CouponService {

	// Attribute

	@Autowired
	private CouponRepository couponRepo;

	@Autowired
	private CompanyRepository companyRepo;
	
	@Autowired
	private IncomeService incomeService;

	@Autowired
	private JpaUtils jpaUtils;

	// Constructor

	/**
	 * Public constructor. Constructs a new {@code CouponService} object.
	 */
	public CouponService() {
	}

	// Methods

	@Transactional
	public void createCoupon(Coupon coupon, long companyID) throws ApplicationException {
		validateIsCouponExists(coupon.getCouponTitle());
		coupon.setCompany(companyRepo.findById(companyID).get());
		couponRepo.save(coupon);
		Income income = new Income(coupon.getCompany().getCompanyName(), Date.valueOf(LocalDate.now()), IncomeDescription.COMPANY_CREATE, 100, coupon.getCompany());
		incomeService.createIncome(income);
		System.out.println("Coupon was created successfuly");
	}

	@Transactional
	public void removeCoupon(long couponID) throws ApplicationException {
		jpaUtils.validateResourceIsPresent(couponRepo, couponID);
		couponRepo.deleteById(couponID);
		System.out.println("Coupon was removed successfuly");
	}

	@Transactional
	public void removeCompanyCoupons(long companyID) throws ApplicationException {
		couponRepo.findByCompany(companyRepo.findById(companyID).get()).forEach(coupon -> couponRepo.delete(coupon));
		System.out.println("Company coupons were removed successfuly");

	}

	@Transactional
	public void updateCoupon(Coupon coupon) throws ApplicationException {
		Coupon updatedCoupon = getCouponByTitle(coupon.getCouponTitle());
		updatedCoupon.setStartDate(coupon.getStartDate());
		updatedCoupon.setEndDate(coupon.getEndDate());
		updatedCoupon.setUnitsInStock(coupon.getUnitsInStock());
		updatedCoupon.setCategoryType(coupon.getCategoryType());
		updatedCoupon.setCouponMessage(coupon.getCouponMessage());
		updatedCoupon.setCouponPrice(coupon.getCouponPrice());
		updatedCoupon.setCouponImage(coupon.getCouponImage());
		couponRepo.save(updatedCoupon);
		Income income = new Income(updatedCoupon.getCompany().getCompanyName(), Date.valueOf(LocalDate.now()), IncomeDescription.COMPANY_UPDATE, 10, updatedCoupon.getCompany());
		incomeService.createIncome(income);
		System.out.println("Coupon was updated successfuly");
	}

	public Coupon getCouponById(long couponID) throws ApplicationException {
		Coupon coupon = couponRepo.findById(couponID).get();
		return coupon;
	}

	public Coupon getCouponByTitle(String couponTitle) throws ApplicationException {
		return couponRepo.findByCouponTitle(couponTitle);
	}

	public List<Coupon> getAllCoupons() throws ApplicationException {
		List<Coupon> couponList = new ArrayList<>();
		couponRepo.findAll().forEach(coupon -> couponList.add(coupon));
		return couponList;
	}

	@Transactional
	public List<Coupon> getCompanyCoupons(long companyID) throws ApplicationException {
		List<Coupon> couponList = new ArrayList<>();
		couponRepo.findByCompany(companyRepo.findById(companyID).get()).forEach(coupon -> couponList.add(coupon));
		return couponList;
	}

	@Transactional
	public List<Coupon> getCompanyCouponsByCategoryType(long companyID, CouponCategoryType categoryType)
			throws ApplicationException {
		List<Coupon> couponList = new ArrayList<>();
		couponRepo.findByCompanyAndCategoryType(companyRepo.findById(companyID).get(), categoryType)
				.forEach(coupon -> couponList.add(coupon));
		return couponList;
	}
	
	public List<Coupon> getCouponsByCategoryType(CouponCategoryType categoryType) throws ApplicationException {
		List<Coupon> couponList = new ArrayList<>();
		couponRepo.findByCategoryType(categoryType)
				.forEach(coupon -> couponList.add(coupon));
		return couponList;
	}

	@Transactional
	public List<Coupon> getCompanyCouponsByMaxPrice(long companyID, double couponPrice) throws ApplicationException {
		List<Coupon> couponList = new ArrayList<>();
		couponRepo.getCompanyCouponsByMaxPrice(companyRepo.findById(companyID).get(), couponPrice)
				.forEach(coupon -> couponList.add(coupon));
		return couponList;
	}
	
	public List<Coupon> getCouponsByMaxPrice(double couponPrice) throws ApplicationException {
		List<Coupon> couponList = new ArrayList<>();
		couponRepo.getCouponsByMaxPrice(couponPrice)
				.forEach(coupon -> couponList.add(coupon));
		return couponList;
	}

	@Transactional
	public List<Coupon> getCompanyCouponsUpToCertainDate(long companyID, Date endDate) throws ApplicationException {
		List<Coupon> couponList = new ArrayList<>();
		couponRepo.getByCompanyAndEndDate(companyRepo.findById(companyID).get(), endDate)
				.forEach(coupon -> couponList.add(coupon));
		return couponList;
	}
	
	public List<Coupon> getCouponsUpToCertainDate(Date endDate) {
		List<Coupon> couponList = new ArrayList<>();
		couponRepo.getByEndDate(endDate)
				.forEach(coupon -> couponList.add(coupon));
		return couponList;
	}

	public void removeAllExpiredCoupons(Date endDate) throws ApplicationException {
		couponRepo.findByEndDateBefore(endDate).forEach(coupon -> couponRepo.delete(coupon));
		System.out.println("Expired coupons were removed successfuly");
	}

	public void validateIsCouponExists(String couponTitle) throws ApplicationException {
		try {
			couponRepo.findByCouponTitle(couponTitle);
		} catch (Exception e) {
			throw new ApplicationException(ErrorType.COUPON_DOES_NOT_EXIST, ErrorType.COUPON_DOES_NOT_EXIST.getExternalMessage());
		}
	}
	
	public boolean checkIfExpired(long couponID) throws ApplicationException {

		boolean expired = true;

		Coupon coupon = getCouponById(couponID);
		Date date = Date.valueOf(LocalDate.now());
		if (date.before(coupon.getEndDate())) {
				expired = false;
			}
	
		return expired;
	}

	

	

	


}
