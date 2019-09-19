package com.CouponSystem.springboot.myCouponSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CouponSystem.springboot.myCouponSystem.entities.Customer;
import com.CouponSystem.springboot.myCouponSystem.exceptions.ApplicationException;
import com.CouponSystem.springboot.myCouponSystem.services.CustomerServise;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	// Attribute

	@Autowired
	private CustomerServise customerServise;

	// Constructor

	// Methods

	@PostMapping
	public void createCustomer(@RequestBody Customer customer) throws ApplicationException {
		customerServise.createCustomer(customer);
	}

	@DeleteMapping("/{customerID}")
	public void removeCustomer(@PathVariable("customerID") long customerID) throws ApplicationException {
		customerServise.removeCustomer(customerID);
	}

	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable("id") long customerID) throws ApplicationException {
		return customerServise.getCustomerById(customerID);
	}

	@GetMapping("/byName")
	public Customer getCustomerByName(@RequestParam("customerName") String customerName) throws ApplicationException {
		return customerServise.getCustomerByName(customerName);
	}

	@GetMapping("byCompanyCustomer")
	public Customer getCompanyCustomer(@RequestParam("companyID") long companyID,
			@RequestParam("customerName") String customerName) throws ApplicationException {
		return customerServise.getCompanyCustomer(companyID, customerName);
	}

	@GetMapping
	public List<Customer> getAllCustomers() throws ApplicationException {
		return customerServise.getAllCustomers();
	}

	@GetMapping("byCompany")
	public List<Customer> getAllCompanyCustomers(@RequestParam("companyID") long companyID)
			throws ApplicationException {
		return customerServise.getAllCompanyCustomers(companyID);
	}

}
