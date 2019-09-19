package com.CouponSystem.springboot.myCouponSystem.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CouponSystem.springboot.myCouponSystem.entities.Customer;
import com.CouponSystem.springboot.myCouponSystem.entities.UserLoginDetails;
import com.CouponSystem.springboot.myCouponSystem.enums.ErrorType;
import com.CouponSystem.springboot.myCouponSystem.exceptions.ApplicationException;
import com.CouponSystem.springboot.myCouponSystem.repositories.CustomerRepository;
import com.CouponSystem.springboot.myCouponSystem.repositories.PurchaseRepository;
import com.CouponSystem.springboot.myCouponSystem.repositories.UserRepository;
import com.CouponSystem.springboot.myCouponSystem.utils.JpaUtils;

@Service
public class CustomerServise {

	// Attribute

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PurchaseRepository purchaseRepo;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private JpaUtils jpaUtils;

	// Constructor

	/**
	 * Public constructor. Constructs a new {@code CustomerServise} object.
	 */
	public CustomerServise() {

	}

	// Methods

	@Transactional
	public void createCustomer(Customer customer) throws ApplicationException {
		validateIsCustomerExists(customer.getCustomerName());
		UserLoginDetails managedUserEntity = userRepo.save(customer.getUser());
		customer.setUser(managedUserEntity);
		customerRepo.save(customer);
		System.out.println("Customer was created successfuly");
	}

	@Transactional
	public void removeCustomer(long customerID) throws ApplicationException {
		jpaUtils.validateResourceIsPresent(customerRepo, customerID);
		customerRepo.deleteById(customerID);

		System.out.println("Customer was removed successfuly");
	}

	public Customer getCustomerById(long customerID) throws ApplicationException {
		Customer customer = customerRepo.findById(customerID).get();
		return customer;
	}

	public Customer getCustomerByName(String customerName) throws ApplicationException {
		Customer customer = customerRepo.findByCustomerName(customerName);
		return customer;
	}

	public Customer getCompanyCustomer(long companyID, String customerName) throws ApplicationException {
		List<Customer> customerList = getAllCompanyCustomers(companyID);
		return customerList.stream().filter(customer -> customer.getCustomerName().equals(customerName)).findFirst()
				.orElse(null);
	}

	public List<Customer> getAllCustomers() throws ApplicationException {
		List<Customer> customerList = new ArrayList<>();
		customerRepo.findAll().forEach(customer -> customerList.add(customer));
		return customerList;
	}

	@Transactional
	public List<Customer> getAllCompanyCustomers(long companyID) throws ApplicationException {
		List<Customer> customerList = new ArrayList<>();
		List<UserLoginDetails> userList = new ArrayList<>();
		purchaseRepo.getCompanyCustomers(companyService.getCompanyById(companyID)).forEach(user -> userList.add(user));

		userList.forEach(user -> {
			try {
				customerList.add(getCustomerById(user.getUserID()));
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		});
		return customerList;
	}

	public void validateIsCustomerExists(String customerName) throws ApplicationException {
		try {
			customerRepo.findByCustomerName(customerName);
		} catch (Exception e) {
			throw new ApplicationException(ErrorType.CUSTOMER_DOES_NOT_EXIST,
					ErrorType.CUSTOMER_DOES_NOT_EXIST.getExternalMessage());
		}
	}

}
