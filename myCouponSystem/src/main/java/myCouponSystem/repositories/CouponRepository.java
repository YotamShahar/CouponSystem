package com.CouponSystem.springboot.myCouponSystem.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.CouponSystem.springboot.myCouponSystem.entities.Company;
import com.CouponSystem.springboot.myCouponSystem.entities.Coupon;
import com.CouponSystem.springboot.myCouponSystem.enums.CouponCategoryType;


@Repository
public interface CouponRepository extends CrudRepository<Coupon, Long>{
	
	public Coupon findByCouponTitle(String couponTitle);

	public List<Coupon> findByCompany(Company company);

	public List<Coupon> findByCompanyAndCategoryType(Company company, CouponCategoryType categoryType);
	
	public List<Coupon> findByCategoryType(CouponCategoryType categoryType);

	@Query("SELECT c FROM Coupon c WHERE company = :company AND couponPrice <= :couponPrice")
	public List<Coupon> getCompanyCouponsByMaxPrice(@Param("company") Company company, @Param("couponPrice")double couponPrice);

	@Query("SELECT c FROM Coupon c WHERE couponPrice <= :couponPrice")
	public List<Coupon> getCouponsByMaxPrice(double couponPrice);

	@Query("SELECT c FROM Coupon c WHERE company = :company AND endDate <= :endDate")
	public List<Coupon> getByCompanyAndEndDate(@Param("company") Company company,  @Param("endDate") Date endDate);

	@Query("SELECT c FROM Coupon c WHERE endDate <= :endDate")
	public List<Coupon> getByEndDate(@Param("endDate") Date endDate);

	public List<Coupon> findByEndDateBefore(Date endDate);



	
	
	
}
