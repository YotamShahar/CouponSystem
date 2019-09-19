package com.CouponSystem.springboot.myCouponSystem.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CouponSystem.springboot.myCouponSystem.beans.CacheData;
import com.CouponSystem.springboot.myCouponSystem.beans.SuccessfulLoginData;
import com.CouponSystem.springboot.myCouponSystem.cache.ICacheController;
import com.CouponSystem.springboot.myCouponSystem.entities.UserLoginDetails;
import com.CouponSystem.springboot.myCouponSystem.enums.ErrorType;
import com.CouponSystem.springboot.myCouponSystem.exceptions.ApplicationException;
import com.CouponSystem.springboot.myCouponSystem.repositories.CompanyRepository;
import com.CouponSystem.springboot.myCouponSystem.repositories.UserRepository;
import com.CouponSystem.springboot.myCouponSystem.utils.JpaUtils;

@Service
public class UserService {

	// Attribute

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CompanyRepository companyRepo;

	@Autowired
	private JpaUtils jpaUtils;

	@Autowired
	private ICacheController cacheController;

	// Constructor

	/**
	 * Public constructor. Constructs a new {@code UserService} object.
	 */
	public UserService() {

	}

	// Methods - Login / Logout

	public SuccessfulLoginData login(String userName, String userPassword) throws ApplicationException {

		UserLoginDetails user = userRepo.findByUserName(userName);

		if (user == null) {
			throw new ApplicationException(ErrorType.ACCOUNT_DOES_NOT_EXIST,
					ErrorType.ACCOUNT_DOES_NOT_EXIST.getExternalMessage());

		}
		int token = generateToken(userName, userPassword);
		String strToken = String.valueOf(token);

		CacheData cacheData = new CacheData(strToken, user.getCompany().getCompanyID(), user.getUserID(),
				user.getUserClientType());

		cacheController.put(strToken, cacheData);

		return new SuccessfulLoginData(user.getUserName(), strToken, user.getUserClientType());
	}

	public void logout(String key) throws Throwable {
		cacheController.remove(key);
	}

	// Methods

	@Transactional
	public void createUser(UserLoginDetails user) throws ApplicationException {
		validateIsUserExists(user.getUserName());
		userRepo.save(user);
		System.out.println("User was created successfuly");
	}

	@Transactional
	public void createEmployee(UserLoginDetails user, long companyID) throws ApplicationException {
		validateIsUserExists(user.getUserName());
		user.setCompany(companyRepo.findById(companyID).get());
		userRepo.save(user);
		System.out.println("User-Employee was created successfuly");
	}

	@Transactional
	public void removeUser(long userID) throws ApplicationException {
		jpaUtils.validateResourceIsPresent(userRepo, userID);
		userRepo.deleteById(userID);
		System.out.println("Coupon was removed successfuly");
	}

	public UserLoginDetails getUserById(long userID) throws ApplicationException {
		UserLoginDetails user = userRepo.findById(userID).get();
		return user;
	}

	public UserLoginDetails getUserByName(String userName) throws ApplicationException {
		return userRepo.findByUserName(userName);
	}

	public List<UserLoginDetails> getAllUsers() throws ApplicationException {
		List<UserLoginDetails> userList = new ArrayList<>();
		userRepo.findAll().forEach(user -> userList.add(user));
		return userList;
	}

	@Transactional
	public List<UserLoginDetails> getAllCompanyEmployees(long companyID) throws ApplicationException {
		List<UserLoginDetails> companyEmployeeList = new ArrayList<>();
		userRepo.findByCompany(companyRepo.findById(companyID).get()).forEach(user -> companyEmployeeList.add(user));
		return companyEmployeeList;
	}

	@Transactional
	public UserLoginDetails getCompanyEmployee(long companyID, String employeeName) throws ApplicationException {
		UserLoginDetails user = userRepo.getCompanyEmployee(companyID, employeeName);
		return user;
	}

	public void validateIsUserExists(String userName) throws ApplicationException {
		try {
			userRepo.findByUserName(userName);
		} catch (Exception e) {
			throw new ApplicationException(ErrorType.USER_DOES_NOT_EXIST,
					ErrorType.USER_DOES_NOT_EXIST.getExternalMessage());
		}
	}

	public boolean isUserLegitimate(String userName, String userPassword) throws ApplicationException {

		UserLoginDetails user = userRepo.findByUserName(userName);

		if (user == null) {
			throw new ApplicationException(ErrorType.ACCOUNT_DOES_NOT_EXIST,
					ErrorType.ACCOUNT_DOES_NOT_EXIST.getExternalMessage());
		}

		if (userPassword.equals(user.getUserPassword())) {
			return true;
		}

		throw new ApplicationException(ErrorType.WORNG_PASSWORD, ErrorType.WORNG_PASSWORD.getExternalMessage());
	}

	private int generateToken(String userName, String userPassword) {

		String salt = "dsfsdfsdfs";
		String token = userName + salt + userPassword;
		return token.hashCode();
	}

}
