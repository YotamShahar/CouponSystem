package com.CouponSystem.springboot.myCouponSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyCouponSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyCouponSystemApplication.class, args);
		
	}

}
