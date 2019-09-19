package com.CouponSystem.springboot.myCouponSystem.beans;

import com.CouponSystem.springboot.myCouponSystem.enums.ClientType;

public class SuccessfulLoginData {
	
	String name;
	String token;
	ClientType clientType;
	
	public SuccessfulLoginData(String name, String token, ClientType clientType) {
		this.name = name;
		this.token = token;
		this.clientType = clientType;
	}

	public SuccessfulLoginData() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}
	
	

}
