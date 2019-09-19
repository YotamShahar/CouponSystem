package com.CouponSystem.springboot.myCouponSystem.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CouponSystem.springboot.myCouponSystem.entities.Coupon;
import com.CouponSystem.springboot.myCouponSystem.entities.Customer;
import com.CouponSystem.springboot.myCouponSystem.entities.Income;
import com.CouponSystem.springboot.myCouponSystem.entities.Purchase;
import com.CouponSystem.springboot.myCouponSystem.enums.CouponCategoryType;
import com.CouponSystem.springboot.myCouponSystem.enums.IncomeDescription;
import com.CouponSystem.springboot.myCouponSystem.exceptions.ApplicationException;
import com.CouponSystem.springboot.myCouponSystem.repositories.PurchaseRepository;
import com.CouponSystem.springboot.myCouponSystem.utils.JpaUtils;


@Service
public class PurchaseService {

	// Attribute

	@Autowired
	private PurchaseRepository purchaseRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private CouponService couponService;
	
	@Autowired
	private IncomeService incomeService;
	
	@Autowired
	private CustomerServise customerServise;
	
	@Autowired
	private CompanyService companyService;

	@Autowired
	private JpaUtils jpaUtils;

	// Constructor

	/**
	 * Public constructor. Constructs a new {@code ProductService} object.
	 */
	public PurchaseService() {

	}

	// Methods

	@Transactional
	public void purchase(Purchase purchase, long userID, long couponID) throws ApplicationException{

			boolean purchaseCoupon = true;
			
			// SOLD-OUT!
			if (!isUnitsInStock(purchase.getPurchaseAmount(), couponID)) {
				purchaseCoupon = false;
			}

			// Expired!
			else if (couponService.checkIfExpired(couponID)) {
				System.out.println("This coupon has EXPIRED!");
				purchaseCoupon = false;
			}

			if (purchaseCoupon) {
				purchase.setUser(userService.getUserById(userID));
				
				// Decrease units in stock!
				Coupon couopn = couponService.getCouponById(couponID);
				couopn.setUnitsInStock(couopn.getUnitsInStock()-purchase.getPurchaseAmount());
				purchase.setCoupon(couopn);
				
				// Set purchase date to current time!
				purchase.setPurchaseDate(Date.valueOf(LocalDate.now()));

				purchaseRepo.save(purchase);
				Customer customer = customerServise.getCustomerById(purchase.getUser().getUserID());
				Income income = new Income(customer.getCustomerName(), Date.valueOf(LocalDate.now()), IncomeDescription.CUSTOMER_PURCHASE, 10*purchase.getPurchaseAmount(), couopn.getCompany());
				incomeService.createIncome(income);
				System.out.println("Purchase was successful");
			
			} 
	}

	@Transactional
	public void removePurchase(long purchaseID) throws ApplicationException {
		jpaUtils.validateResourceIsPresent(purchaseRepo, purchaseID);
		purchaseRepo.deleteById(purchaseID);
		System.out.println("Purchase was removed successfuly");
	}

	@Transactional
	public void removeCustomerPurchases(long userID) throws ApplicationException {
		purchaseRepo.findByUser(userService.getUserById(userID)).forEach(purchase -> purchaseRepo.delete(purchase));
		System.out.println("Company coupons were removed successfuly");
	}

	@Transactional
	public void updatePurchase(Purchase purchase) throws ApplicationException {
		jpaUtils.validateResourceIsPresent(purchaseRepo, purchase.getPurchaseID());
		purchaseRepo.save(purchase);
		System.out.println("Purchase was updated successfuly");
	}

	public Purchase getPurchaseById(long purchaseID) throws ApplicationException {
		Purchase purchase = purchaseRepo.findById(purchaseID).get();
		return purchase;
	}

	public List<Purchase> getAllPurchase() throws ApplicationException {
		List<Purchase> purchaseList = new ArrayList<>();
		purchaseRepo.findAll().forEach(purchase -> purchaseList.add(purchase));
		return purchaseList;
	}

	@Transactional
	public List<Purchase> getCustomerPurchases(long userID) throws ApplicationException {
		List<Purchase> purchaseList = new ArrayList<>();
		purchaseRepo.findByUser(userService.getUserById(userID)).forEach(purchase -> purchaseList.add(purchase));
		return purchaseList;
	}
	
	@Transactional
	public List<Purchase> getCompanyPurchases(long companyID) throws ApplicationException {
		List<Purchase> purchaseList = new ArrayList<>();
		purchaseRepo.getCompanyPurchases(companyService.getCompanyById(companyID)).forEach(purchase -> purchaseList.add(purchase));
		return purchaseList;
	}

	@Transactional
	public List<Purchase> getByCategoryType(CouponCategoryType categoryType)
			throws ApplicationException {
		List<Purchase> purchaseList = new ArrayList<>();
		purchaseRepo.getByCategoryType(categoryType).forEach(purchase -> purchaseList.add(purchase));
		return purchaseList;
	}
	
	@Transactional
	public List<Purchase> getByMaxPrice(double couponPrice)
			throws ApplicationException {
		List<Purchase> purchaseList = new ArrayList<>();
		purchaseRepo.getByMaxPrice(couponPrice).forEach(purchase -> purchaseList.add(purchase));
		return purchaseList;
	}

	public boolean isUnitsInStock(int purchaseAmount, long couponID) throws ApplicationException{
	
			
			Coupon coupon = couponService.getCouponById(couponID);

			if(purchaseAmount <= coupon.getUnitsInStock()) {
		
			return true;
			} else {
				System.out.println("There is not enough units in stock.\nCurrent units in stock: "+coupon.getUnitsInStock()+".");
				return false;
			}
		}	
}
