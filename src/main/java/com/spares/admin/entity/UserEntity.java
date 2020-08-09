package com.spares.admin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
public class UserEntity {
	
	@Id
	private Integer userId;
	private String userName;
	private String companyName;
	private String contactNumber;
	private String address;
	private String state;
	private String country;
	private String dateOfBirth;
	private String userRole;
	private String emailAddress;
	private String password;
	public UserEntity(Integer userId, String userName, String companyName, String contactNumber, String address,
			String state, String country, String dateOfBirth, String userRole, String emailAddress, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.companyName = companyName;
		this.contactNumber = contactNumber;
		this.address = address;
		this.state = state;
		this.country = country;
		this.dateOfBirth = dateOfBirth;
		this.userRole = userRole;
		this.emailAddress = emailAddress;
		this.password = password;
	}
	public UserEntity() {
		super();
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
