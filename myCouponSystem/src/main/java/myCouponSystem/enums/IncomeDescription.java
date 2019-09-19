package com.CouponSystem.springboot.myCouponSystem.enums;

/**
 * This enum class {@code IncomeDescription} serves the purpose of representing
 * a group of named constants of incomed escriptions in the
 * {@code coupon.system.spring} project.
 * 
 * @author DaniellaDavis
 */
public enum IncomeDescription {

	CUSTOMER_PURCHASE("Customer - Coupon purchase"),
	COMPANY_CREATE("Company - New coupon"),
	COMPANY_UPDATE("Company - Coupon update");

	
	private String displayDescription;

	IncomeDescription(String displayDescription) {
		this.displayDescription = displayDescription;
	}

	public String displayDescription() {
		return displayDescription;
	}

	@Override
	public String toString() {
		return displayDescription;
	}

}
