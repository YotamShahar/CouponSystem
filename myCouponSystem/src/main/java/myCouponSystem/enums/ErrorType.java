package com.CouponSystem.springboot.myCouponSystem.enums;

public enum ErrorType {	

//	INVALID_USER(601, "Invalid user error"), 
//	ILLEGAL_USER_INPUT(602, "Illegal user input"),
//	USER_ERROR(602, "User error"),
//	WORNG_INPUT(602, "Wrong input"),
//	EMAIL_ALREADY_EXISTS(603, "Email alreay exists"),
	ITEM_DOES_NOT_EXIST(false, 600, "Item does not exists"),
	COMPANY_DOES_NOT_EXIST(false, 601, "Company does not exists"),
	COUPON_DOES_NOT_EXIST(false, 602, "Coupon does not exists"),
	CUSTOMER_DOES_NOT_EXIST(false, 603, "Customer does not exists"),
	USER_DOES_NOT_EXIST(false, 604, "User does not exists"),
	ACCOUNT_DOES_NOT_EXIST(false, 605, "Account does not exists"),
	WORNG_PASSWORD(false, 606, "Wrong password"), 
	GENERAL_ERROR(true, 607, "General error");
//	OUT_OF_STOCK_OR_EXPIRED(605, "Out of stock or expired"), 
//	CANNOT_PARSE_DATE(602,"Cannot parse date"),
//	START_DATE_BIGGER_THAN_END_DATE(605, "Start date bigger than end date"), 
//	DB_ERROR(606, "Database error"), 
//	DATA_NOT_FOUND(607, "Data not found"), 
//	HACKING_ATTEMPT(800, "Hacking attempt detected");
	
	// Attribute
	
	private int internalErrorCode;
	private String externalMessage;
	private boolean isPrintStackTrace;


	//private Constructor 

	private ErrorType(boolean isPrintStackTrace, int internalErrorCode, String internalMessage){
		this.isPrintStackTrace = isPrintStackTrace;
		this.internalErrorCode = internalErrorCode;
		this.externalMessage = internalMessage;

	}

	// Getters
	
	public boolean isPrintStackTrace() {
		return isPrintStackTrace;
	}

	public int getInternalErrorCode() {
		return internalErrorCode;
	}

	public String getExternalMessage() {
		return externalMessage;
	}

}
