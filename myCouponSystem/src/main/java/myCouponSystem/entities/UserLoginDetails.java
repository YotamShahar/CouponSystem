package com.CouponSystem.springboot.myCouponSystem.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.CouponSystem.springboot.myCouponSystem.enums.ClientType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

//Table
@Entity
@Table(name = "users")
public class UserLoginDetails implements Serializable{

	// Columns

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private long userID;

	@Column(name = "user_name", unique = true, nullable = false, length = 40)
	private String userName;

	@Column(name = "user_client_type", nullable = false, length = 20)
	private ClientType userClientType;

	@Column(name = "user_password", nullable = false, length = 10)
	private String userPassword;
	

	// Hibernate mapping

	@ManyToOne
	@JsonIgnore
	private Company company;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Purchase> usersPurchases;

	// Constructors

	/**
	 * Public no-argument constructor. Constructs a new {@code UserLoginDetails}
	 * object with no params.
	 */
	public UserLoginDetails() {
	}

	/**
	 * Public constructor. Constructs a new {@code UserLoginDetails} object with the
	 * following params.
	 * 
	 * @param userName       The {@code UserLoginDetails} object name.
	 * @param userClientType {@code UserLoginDetails} object client type.
	 * @param userPassword   {@code UserLoginDetails} object password.
	 */
	public UserLoginDetails(String userName, ClientType userClientType, String userPassword) {
		this.userName = userName;
		this.userClientType = userClientType;
		this.userPassword = userPassword;
	}

	// Getters & Setters
	
	/**
	 * Gets the {@code UserLoginDetails}'s ID.
	 * 
	 * @return A long representing the {@code UserLoginDetails}'s ID.
	 */
	public long getUserID() {
		return userID;
	}

	/**
	 * Sets the {@code UserLoginDetails}'s ID.
	 * 
	 * @param userID A long containing the {@code UserLoginDetails}'s ID.
	 */
	public void setUserID(long userID) {
		this.userID = userID;
	}

	/**
	 * Gets the {@code UserLoginDetails}'s name.
	 * 
	 * @return A String representing the {@code UserLoginDetails}'s name.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the {@code UserLoginDetails}'s name.
	 * 
	 * @param userName A String containing the {@code UserLoginDetails}'s name.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the {@code UserLoginDetails}'s client type.
	 * 
	 * @return An Enum representing the {@code UserLoginDetails}'s client type.
	 */
	public ClientType getUserClientType() {
		return userClientType;
	}

	/**
	 * Sets the {@code UserLoginDetails}'s client type.
	 * 
	 * @param userClientType An Enum containing the {@code UserLoginDetails}'s client type.
	 */
	public void setUserClientType(ClientType userClientType) {
		this.userClientType = userClientType;
	}

	/**
	 * Gets the {@code UserLoginDetails}'s password.
	 * 
	 * @return A String representing the {@code UserLoginDetails}'s password.
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * Sets the {@code UserLoginDetails}'s password.
	 * 
	 * @param userPassword a String containing the {@code UserLoginDetails}'s password.
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * Gets the {@code UserLoginDetails}'s company.
	 * 
	 * @return A Company representing the {@code UserLoginDetails}'s company.
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Sets the {@code UserLoginDetails}'s company.
	 * 
	 * @param company a Company containing the {@code UserLoginDetails}'s company.
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * Returns a String representation of the {@code UserLoginDetails} object. This
	 * {@code toString} method returns a String that "textually represents" the
	 * {@code UserLoginDetails} object and includes the following attributes:
	 * [<b>userID</b>, <b>userName</b>, <b>userClientType</b>, <b>userPassword</b>,
	 * <b>company</b>]. The result should be a concise but informative
	 * representation that is easy for a person to read.
	 * 
	 * @return a String representation of the {@code UserLoginDetails} object.
	 */
	@Override
	public String toString() {
		return "UserLoginDetails [userId=" + userID + ", userName=" + userName + ", userClientType=" + userClientType
				+ ", userPassword=" + userPassword + ", company=" + company + "]";
	}

}
