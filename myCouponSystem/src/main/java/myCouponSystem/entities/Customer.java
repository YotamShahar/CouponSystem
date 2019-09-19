package com.CouponSystem.springboot.myCouponSystem.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

//Table
@Entity
@Table(name = "customers")
public class Customer implements Serializable{

	// Columns
	
	@Id
    private long customerID;
	
	@Column(name = "customer_name", unique = true, nullable = false, length = 40)
	private String customerName;

	// Hibernate mapping
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@JoinColumn
    @MapsId
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private UserLoginDetails user;

	// Constructor

	/**
	 * Public no-argument constructor. Constructs a new {@code Customer} object with
	 * no params.
	 */
	public Customer() {
	}

	/**
	 * Public constructor. Constructs a new {@code Customer} object with the
	 * following params.
	 * 
	 * @param customerName The {@code Customer} object name.
	 */
	public Customer(String customerName) {
		this.customerName = customerName;
	}

	// Getters & Setters

	/**
	 * Gets the {@code Customer}'s name.
	 * 
	 * @return A String representing the {@code Customer}'s name.
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Sets the {@code Customer}'s name.
	 * 
	 * @param customerName A String containing the {@code Customer}'s name.
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	/**
	 * Gets the {@code Customer}'s user.
	 * 
	 * @return A UserLoginDetails representing the {@code Customer}'s user.
	 */
	public UserLoginDetails getUser() {
		return user;
	}

	/**
	 * Sets the {@code Customer}'s user.
	 * 
	 * @param user A UserLoginDetails containing the {@code Customer}'s user.
	 */
	public void setUser(UserLoginDetails user) {
		this.user = user;
	}

	// Methods

	

	/**
	 * Returns a String representation of the {@code Customer} object. This
	 * {@code toString} method returns a String that "textually represents" the
	 * {@code Customer} object and includes the following attributes:
	 * [<b>customerName</b>, <b>user</b>]. The result should be a concise but
	 * informative representation that is easy for a person to read.
	 * 
	 * @return a String representation of the {@code Customer} object.
	 */
	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + "user="+user+"]";
	}

}
