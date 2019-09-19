package com.CouponSystem.springboot.myCouponSystem.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CouponSystem.springboot.myCouponSystem.beans.CacheData;
import com.CouponSystem.springboot.myCouponSystem.beans.SuccessfulLoginData;
import com.CouponSystem.springboot.myCouponSystem.beans.UserLoginData;
import com.CouponSystem.springboot.myCouponSystem.consts.Consts;
import com.CouponSystem.springboot.myCouponSystem.entities.UserLoginDetails;
import com.CouponSystem.springboot.myCouponSystem.enums.ErrorType;
import com.CouponSystem.springboot.myCouponSystem.exceptions.ApplicationException;
import com.CouponSystem.springboot.myCouponSystem.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	// Attribute

	@Autowired
	private UserService userService;

	// Constructor

	// Methods - Login / Logout
	@PostMapping("/login")
	public SuccessfulLoginData login (@RequestBody UserLoginData loginData) throws ApplicationException {
		return userService.login(loginData.getUserName(), loginData.getUserPassword());
		
	}

	@GetMapping("/logout")
	public void logout (HttpServletRequest request)  throws Throwable {
		CacheData cacheData = (CacheData) request.getAttribute(Consts.USER_DATA_KEY);
		userService.logout(cacheData.getToken());
	}

	
	
	
	// Methods
	@PostMapping
	public void createUser(@RequestBody UserLoginDetails user) throws ApplicationException {
		userService.createUser(user);
	}
	
	@PostMapping("/{companyID}")
	public void createEmployee(@RequestBody UserLoginDetails user, @PathVariable("companyID") long companyID) throws ApplicationException {
		userService.createEmployee(user, companyID);
	}

	@DeleteMapping("/{userID}")
	public void removeUser(@PathVariable("userID") long userID) throws ApplicationException {
		userService.removeUser(userID);
	}
	
	@GetMapping("/{userID}")
	public UserLoginDetails getUserById(@PathVariable("userID") long userID) throws ApplicationException {
		return userService.getUserById(userID);
	}
	
	@GetMapping("/byName")
	public UserLoginDetails getUserByName(@RequestParam("userName") String userName) throws ApplicationException {
		return userService.getUserByName(userName);
	}
	
	@GetMapping
	public List<UserLoginDetails> getAllUsers() throws ApplicationException{
		return userService.getAllUsers();
	}	
	
	@GetMapping("/byCompany")
	public List<UserLoginDetails> getAllCompanyEmployees(@RequestParam("companyID") long companyID) throws ApplicationException {
		return userService.getAllCompanyEmployees(companyID);
	}
	
	@GetMapping("/byEmployeeName")
	public UserLoginDetails getCompanyEmployee(@RequestParam("companyID") long companyID, @RequestParam("employeeName") String employeeName) throws ApplicationException {
		return userService.getCompanyEmployee(companyID, employeeName);
	}

}
