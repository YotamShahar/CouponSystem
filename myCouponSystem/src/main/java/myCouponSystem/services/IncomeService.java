package com.CouponSystem.springboot.myCouponSystem.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CouponSystem.springboot.myCouponSystem.entities.Income;
import com.CouponSystem.springboot.myCouponSystem.enums.IncomeDescription;
import com.CouponSystem.springboot.myCouponSystem.exceptions.ApplicationException;
import com.CouponSystem.springboot.myCouponSystem.repositories.CompanyRepository;
import com.CouponSystem.springboot.myCouponSystem.repositories.IncomeRepository;
import com.CouponSystem.springboot.myCouponSystem.utils.JpaUtils;

@Service
public class IncomeService {
	
	// Attribute

		@Autowired
		private IncomeRepository incomeRepo;
		
		@Autowired
		private CompanyRepository companyRepo;
		
		@Autowired
		private JpaUtils jpaUtils;

		// Constructor

		/**
		 * Public constructor. Constructs a new {@code CompanyService} object.
		 */
		public IncomeService() {

		}

		// Methods


	public void createIncome(Income income) throws ApplicationException {
		incomeRepo.save(income);
		System.out.println("Income was created successfuly");	
	}

	@Transactional
	public void removeIncome(long incomeID) throws ApplicationException {
		jpaUtils.validateResourceIsPresent(incomeRepo, incomeID);
		incomeRepo.deleteById(incomeID);
		System.out.println("Income was removed successfuly");
	}

	public Income getIncomeById(long incomeID) throws ApplicationException {
		Income income = incomeRepo.findById(incomeID).get();
		return income;
	}

	public List<Income> getAllIncomes() throws ApplicationException {
		List<Income> incomeList = new ArrayList<>();
		incomeRepo.findAll().forEach(income -> incomeList.add(income));
		return incomeList;	}

	@Transactional
	public List<Income> getIncomesByCompany(long companyID) throws ApplicationException {
		List<Income> incomeList = new ArrayList<>();
		incomeRepo.findByCompany(companyRepo.findById(companyID).get()).forEach(income -> incomeList.add(income));
		return incomeList;
	}

	public List<Income> getIncomesByDescription(IncomeDescription incomeDescription) {
		List<Income> incomeList = new ArrayList<>();
		incomeRepo.findByIncomeDescription(incomeDescription).forEach(income -> incomeList.add(income));
		return incomeList;
	}

}
