package com.CouponSystem.springboot.myCouponSystem.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CouponSystem.springboot.myCouponSystem.entities.Coupon;
import com.CouponSystem.springboot.myCouponSystem.enums.CouponCategoryType;
import com.CouponSystem.springboot.myCouponSystem.exceptions.ApplicationException;
import com.CouponSystem.springboot.myCouponSystem.services.CouponService;

@RestController
@RequestMapping("/coupons")
public class CouponController {

	// Attribute

	@Autowired
	private CouponService couponService;

	// Constructor

	// Methods

	@PostMapping("/{companyID}")
	public void createCoupon(@RequestBody Coupon coupon, @PathVariable("companyID") long companyID)
			throws ApplicationException {
		couponService.createCoupon(coupon, companyID);
	}

	@DeleteMapping("/{couponID}")
	public void removeCoupon(@PathVariable("couponID") long couponID) throws ApplicationException {
		couponService.removeCoupon(couponID);
	}

	@DeleteMapping("/byCompany/{companyID}")
	public void removeCompanyCoupons(@PathVariable("companyID") long companyID) throws ApplicationException {
		couponService.removeCompanyCoupons(companyID);
	}

	@PutMapping
	public void updateCoupon(@RequestBody Coupon coupon) throws ApplicationException {
		couponService.updateCoupon(coupon);
	}

	@GetMapping("/{couponID}")
	public Coupon getCouponById(@PathVariable("couponID") long couponID) throws ApplicationException {
		return couponService.getCouponById(couponID);
	}

	@GetMapping("/byTitle")
	public Coupon getCouponByTitle(@RequestParam("couponTitle") String couponTitle) throws ApplicationException {
		return couponService.getCouponByTitle(couponTitle);
	}

	@GetMapping
	public List<Coupon> getAllCoupons() throws ApplicationException {
		return couponService.getAllCoupons();
	}

	@GetMapping("/byCompany")
	public List<Coupon> getCompanyCoupons(@RequestParam("companyID") long companyID) throws ApplicationException {
		return couponService.getCompanyCoupons(companyID);
	}

	@GetMapping("/byCompanyCategory")
	public List<Coupon> getCompanyCouponsByCategoryType(@RequestParam("companyID") long companyID,
			@RequestParam("categoryType") CouponCategoryType categoryType) throws ApplicationException {
		return couponService.getCompanyCouponsByCategoryType(companyID, categoryType);
	}
	
	@GetMapping("/byCategory")
	public List<Coupon> getCouponsByCategoryType(@RequestParam("categoryType") CouponCategoryType categoryType) throws ApplicationException {
		return couponService.getCouponsByCategoryType(categoryType);
	}

	@GetMapping("/byCompanyMaxPrice")
	public List<Coupon> getCompanyCouponsByMaxPrice(@RequestParam("companyID") long companyID,
			@RequestParam("couponPrice") double couponPrice) throws ApplicationException {
		return couponService.getCompanyCouponsByMaxPrice(companyID, couponPrice);
	}
	
	@GetMapping("/byMaxPrice")
	public List<Coupon> getCouponsByMaxPrice(@RequestParam("couponPrice") double couponPrice) throws ApplicationException {
		return couponService.getCouponsByMaxPrice(couponPrice);
	}

	@GetMapping("/byCompanyDate")
	public List<Coupon> getCompanyCouponsUpToCertainDate(@RequestParam("companyID") long companyID,
			@RequestParam("endDate") Date endDate) throws ApplicationException {
		return couponService.getCompanyCouponsUpToCertainDate(companyID, endDate);
	}
	
	@GetMapping("/byDate")
	public List<Coupon> getCouponsUpToCertainDate(@RequestParam("endDate") Date endDate) throws ApplicationException {
		return couponService.getCouponsUpToCertainDate(endDate);
	}

}
