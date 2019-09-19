package com.CouponSystem.springboot.myCouponSystem.exceptions;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CouponSystem.springboot.myCouponSystem.beans.ErrorBean;
import com.CouponSystem.springboot.myCouponSystem.enums.ErrorType;

@ControllerAdvice
public class ExceptionHandler extends Exception {

	    // Handles ApplicationExceptions
	
		@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
		public @ResponseBody ErrorBean handleConflict(HttpServletResponse response, Exception e) {
			response.setStatus(600);
			String errorMessage;
			if(e instanceof ApplicationException) {
				ApplicationException e2 = (ApplicationException) e;
				errorMessage = e2.getMessage();
				if(e2.getErrorType().isPrintStackTrace()) {
					e.printStackTrace();
				}
				return new ErrorBean(errorMessage, e2.getErrorType().getInternalErrorCode());
			}
			e.printStackTrace();
			return new ErrorBean(ErrorType.GENERAL_ERROR.getExternalMessage(), ErrorType.GENERAL_ERROR.getInternalErrorCode());
//			else {
//				errorMessage = "General error!";
//			}
//			
//			
//			response.setStatus(e.getErrorType().getInternalErrorCode());
//			response.setHeader("errorMessage", errorMessage);
		}
		
		// Handles all other exceptions
	
//		@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
//		public void handleConflict(HttpServletResponse response, Exception e) {
//			e.printStackTrace();
//			String internalMessage = e.getMessage();
//			response.setStatus(500);
//			response.setHeader("errorMessage", internalMessage);
//		}

}
