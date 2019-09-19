package com.CouponSystem.springboot.myCouponSystem.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CouponSystem.springboot.myCouponSystem.entities.Company;
import com.CouponSystem.springboot.myCouponSystem.enums.ErrorType;
import com.CouponSystem.springboot.myCouponSystem.exceptions.ApplicationException;
import com.CouponSystem.springboot.myCouponSystem.repositories.CompanyRepository;
import com.CouponSystem.springboot.myCouponSystem.utils.JpaUtils;

@Service
public class CompanyService {

	// Attribute

	@Autowired
	private CompanyRepository companyRepo;

	@Autowired
	private JpaUtils jpaUtils;

	// Constructor

	/**
	 * Public constructor. Constructs a new {@code CompanyService} object.
	 */
	public CompanyService() {

	}

	// Methods
	
	@Transactional
	public void createCompany(Company company) throws ApplicationException {
		validateIsCompanyExists(company.getCompanyName());
		companyRepo.save(company);
		System.out.println("Company was created successfuly");

	}

	@Transactional
	public void removeCompany(long companyID) throws ApplicationException {
		jpaUtils.validateResourceIsPresent(companyRepo, companyID);
		companyRepo.deleteById(companyID);
		System.out.println("Company was removed successfuly");

	}
	
	@Transactional
	public void updateCompany(Company company) throws ApplicationException {
		Company updatedCompany = getCompanyByName(company.getCompanyName());
		updatedCompany.setCompanyEmail(company.getCompanyEmail());
		companyRepo.save(updatedCompany);
		System.out.println("Company was updated successfuly");
	}

	public Company getCompanyById(long companyId) throws ApplicationException {
		Company company = companyRepo.findById(companyId).get();
		return company;
	}

	public Company getCompanyByName(String companyName) throws ApplicationException {
		Company company = companyRepo.findByCompanyName(companyName);
		return company;
	}

	public List<Company> getAllCompanies() throws ApplicationException {
		List<Company> companyList = new ArrayList<>();
		companyRepo.findAll().forEach(company -> companyList.add(company));
		return companyList;
	}

	// Util methods
	
	public void validateIsCompanyExists(String companyName) throws ApplicationException {
		try {
			companyRepo.findByCompanyName(companyName);
		} catch (Exception e) {
			throw new ApplicationException(ErrorType.COMPANY_DOES_NOT_EXIST, ErrorType.COMPANY_DOES_NOT_EXIST.getExternalMessage());
		}
	}

}
