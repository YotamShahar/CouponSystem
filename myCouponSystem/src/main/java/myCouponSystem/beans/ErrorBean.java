package com.CouponSystem.springboot.myCouponSystem.beans;

public class ErrorBean {
	
	private String errorMessage;
	private int internalErrorCode;
	
	public ErrorBean(String errorMessage, int internalErrorCode) {
		this.errorMessage = errorMessage;
		this.internalErrorCode = internalErrorCode;
	}

	public ErrorBean() {
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getInternalErrorCode() {
		return internalErrorCode;
	}

	public void setInternalErrorCode(int internalErrorCode) {
		this.internalErrorCode = internalErrorCode;
	}
	

	
}
