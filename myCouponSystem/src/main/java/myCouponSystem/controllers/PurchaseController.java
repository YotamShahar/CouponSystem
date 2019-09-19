package com.CouponSystem.springboot.myCouponSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CouponSystem.springboot.myCouponSystem.entities.Purchase;
import com.CouponSystem.springboot.myCouponSystem.enums.CouponCategoryType;
import com.CouponSystem.springboot.myCouponSystem.exceptions.ApplicationException;
import com.CouponSystem.springboot.myCouponSystem.services.PurchaseService;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

	// Attribute

	@Autowired
	private PurchaseService purchaseService;

	// Constructor

	// Methods

	@PostMapping("/{userID}/{couponID}")
	public void purchase(@RequestBody Purchase purchase, @PathVariable("userID") long userID, @PathVariable("couponID") long couponID) throws ApplicationException {
		purchaseService.purchase(purchase, userID, couponID);
	}
	
	@DeleteMapping("/{purchaseID}")
	public void removePurchase(@PathVariable("purchaseID") long purchaseID) throws ApplicationException {
		purchaseService.removePurchase(purchaseID);
	}
	
	@DeleteMapping("/byCustomer")
	public void removeCustomerPurchases(@RequestParam("userID") long userID) throws ApplicationException {
		purchaseService.removeCustomerPurchases(userID);
	}
	
	@GetMapping("/{purchaseID}")
	public Purchase getPurchaseById(@PathVariable("purchaseID") long purchaseID) throws ApplicationException {
		return purchaseService.getPurchaseById(purchaseID);
	}
	
	@GetMapping
	public List<Purchase> getAllPurchase() throws ApplicationException {
		return purchaseService.getAllPurchase();
	}
	
	@GetMapping("/customerPurchases")
	public List<Purchase> getCustomerPurchases(@RequestParam("userID") long userID) throws ApplicationException {
		return purchaseService.getCustomerPurchases(userID);
	}
	
	@GetMapping("/companyPurchases")
	public List<Purchase> getCompanyPurchases(@RequestParam("companyID") long companyID) throws ApplicationException {
		return purchaseService.getCompanyPurchases(companyID);
	}
	
	@GetMapping("/byCategory")
	public List<Purchase> getByCategoryType(@RequestParam("categoryType") CouponCategoryType categoryType) throws ApplicationException {
		return purchaseService.getByCategoryType(categoryType);
	}

	@GetMapping("/byMaxPrice")
	public List<Purchase> getByMaxPrice(@RequestParam("couponPrice") double couponPrice) throws ApplicationException {
		return purchaseService.getByMaxPrice(couponPrice);
	}

}
