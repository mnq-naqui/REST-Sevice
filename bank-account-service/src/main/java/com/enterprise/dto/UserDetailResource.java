package com.enterprise.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;


public class UserDetailResource extends ResourceSupport implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -129921110945252272L;
	
	public UserDetailResource() {
		
	}

	private Integer userId;
	private String userName;
	private String email;
	private String mobile;
	private String currentAddress;
	private String permanentAddress;
	private Integer cityRef;
	private Integer stateRef;
	private String pincode;
	private List<BankResource> banks;

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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public Integer getCityRef() {
		return cityRef;
	}
	public void setCityRef(Integer cityRef) {
		this.cityRef = cityRef;
	}
	public Integer getStateRef() {
		return stateRef;
	}
	public void setStateRef(Integer stateRef) {
		this.stateRef = stateRef;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public List<BankResource> getBanks() {
		return banks;
	}
	public void setBanks(List<BankResource> banks) {
		this.banks = banks;
	}
	public UserDetailResource(Integer userId, String userName, String email, String mobile, String currentAddress,
			String permanentAddress, Integer cityRef, Integer stateRef, String pincode) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.mobile = mobile;
		this.currentAddress = currentAddress;
		this.permanentAddress = permanentAddress;
		this.cityRef = cityRef;
		this.stateRef = stateRef;
		this.pincode = pincode;
	}
	
}
