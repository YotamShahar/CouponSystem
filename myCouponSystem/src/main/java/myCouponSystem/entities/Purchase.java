package com.CouponSystem.springboot.myCouponSystem.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

//Table
@Entity
@Table(name = "purchases")
public class Purchase implements Serializable{

	// Columns

	@Id
	@GeneratedValue
	@Column(name = "purchase_id")
	private long purchaseID;

	@Column(nullable = false, name = "purchase_date")
	private Date purchaseDate;

	@Column(nullable = false, name = "purchase_amount")
	private int purchaseAmount;

	// Hibernate mapping

	@ManyToOne
	@JsonIgnore
	@Fetch(FetchMode.JOIN)
	private Coupon coupon;

//	@ManyToOne
//	@JsonIgnore
//	private Customer customer;

	@ManyToOne
	@JsonIgnore
	@Fetch(FetchMode.JOIN)
	private UserLoginDetails user;

	// Constructors

	/**
	 * Public no-argument constructor. Constructs a new {@code Coupon} object with
	 * no params.
	 */
	public Purchase() {
	}

	/**
	 * Public constructor. Constructs a new {@code Purchase} object with the
	 * following params.
	 * 
	 * @param purchaseDate   The {@code Purchase} object date.
	 * @param purchaseAmount The {@code Purchase} object amount.
	 */
	public Purchase(Date purchaseDate, int purchaseAmount) {
		this.purchaseDate = purchaseDate;
		this.purchaseAmount = purchaseAmount;
	}

	// Getters & Setters

	/**
	 * Gets the {@code Purchase}'s ID.
	 * 
	 * @return A long representing the {@code Purchase}'s ID.
	 */
	public long getPurchaseID() {
		return purchaseID;
	}

	/**
	 * Sets the {@code Purchase}'s ID.
	 * 
	 * @param purchaseID A long containing the {@code Purchase}'s ID.
	 */
	public void setPurchaseID(long purchaseID) {
		this.purchaseID = purchaseID;
	}

	/**
	 * Gets the {@code Purchase}'s date.
	 * 
	 * @return A Date representing the {@code Purchase}'s date.
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * Sets the {@code Purchase}'s date.
	 * 
	 * @param purchaseDate A Date containing the {@code Purchase}'s date.
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/**
	 * Gets the {@code Purchase}'s amount.
	 * 
	 * @return An int representing the {@code Purchase}'s amount.
	 */
	public int getPurchaseAmount() {
		return purchaseAmount;
	}

	/**
	 * Sets the {@code Purchase}'s amount.
	 * 
	 * @param purchaseAmount An int containing the {@code Purchase}'s amount.
	 */
	public void setPurchaseAmount(int purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	/**
	 * Gets the {@code Purchase}'s coupon.
	 * 
	 * @return A Coupon representing the {@code Purchase}'s coupon.
	 */
	public Coupon getCoupon() {
		return coupon;
	}

	/**
	 * Sets the {@code Purchase}'s coupon.
	 * 
	 * @param coupon a Coupon containing the {@code Purchase}'s coupon.
	 */
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	/**
	 * Gets the {@code Purchase}'s user.
	 * 
	 * @return A UserLoginDetails representing the {@code Purchase}'s user.
	 */
	public UserLoginDetails getUser() {
		return user;
	}

	/**
	 * Sets the {@code Purchase}'s user.
	 * 
	 * @param user a UserLoginDetails containing the {@code Purchase}'s user.
	 */
	public void setUser(UserLoginDetails user) {
		this.user = user;
	}

	// Methods

	/**
	 * Returns a String representation of the {@code Purchase} object. This
	 * {@code toString} method returns a String that "textually represents" the
	 * {@code Purchase} object and includes the following attributes:
	 * [<b>purchaseID</b>, <b>purchaseDate</b>, <b>purchaseAmount</b>, <b>coupon</b>,
	 * <b>user</b>]. The result should be a concise but informative
	 * representation that is easy for a person to read.
	 * 
	 * @return a String representation of the {@code Purchase} object.
	 */
	@Override
	public String toString() {
		return "Purchase [purchaseId=" + purchaseID + ", purchaseDate=" + purchaseDate + ", purchaseAmount="
				+ purchaseAmount + ", coupon=" + coupon + ", user=" + user + "]";
	}

}
