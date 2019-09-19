package com.CouponSystem.springboot.myCouponSystem.utils;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.CouponSystem.springboot.myCouponSystem.enums.ErrorType;
import com.CouponSystem.springboot.myCouponSystem.exceptions.ApplicationException;

@Repository
public class JpaUtils {
	
	public void validateResourceIsPresent(CrudRepository<?, Long> repository, long id) {
		boolean isPresent = repository.findById(id).isPresent();
		if (!isPresent) {
				try {
					throw new ApplicationException(ErrorType.ITEM_DOES_NOT_EXIST, ErrorType.ITEM_DOES_NOT_EXIST.getExternalMessage());
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
		}
	}
	
}
