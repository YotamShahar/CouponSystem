package com.CouponSystem.springboot.myCouponSystem.beans;

import java.io.Serializable;

import com.CouponSystem.springboot.myCouponSystem.enums.ClientType;

@SuppressWarnings("serial")
public class CacheData implements Serializable{
	
	private String token;
	private Long companyID;
	private long userID;
	private ClientType clientType;
	
	
	
	public CacheData() {
	}


	public CacheData(String token,Long companyID, long userID, ClientType clientType) {
		this.token = token;
		this.companyID = companyID;
		this.userID = userID;
		this.clientType = clientType;
	}


	public Long getCompanyID() {
		return companyID;
	}


	public void setCompanyID(Long companyID) {
		this.companyID = companyID;
	}


	public long getUserID() {
		return userID;
	}


	public void setUserID(long userID) {
		this.userID = userID;
	}


	public ClientType getClientType() {
		return clientType;
	}


	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}
	
	



}
