package com.CouponSystem.springboot.myCouponSystem.beans;

public class UserLoginData {
	
	private String userName;
	private String userPassword;
	
	public UserLoginData(String userName, String userPassword) {
		this.userName = userName;
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	


}
