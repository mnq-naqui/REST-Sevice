package com.enterprise.entity;

import java.io.Serializable;
import java.util.List;

public class UserDetail implements Serializable{
	
	private static final long serialVersionUID = 5436367041276584029L;
	private Integer userId;
	private String userName;
	private String email;
	private String mobile;
	private String currentAddress;
	private String permanentAddress;
	private Integer cityRef;
	private Integer stateRef;
	private String pincode;
	private Integer bankRef;
	private Integer branchRef;
	private List<Bank> banks;
	
	public UserDetail() {
		
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

	public Integer getBankRef() {
		return bankRef;
	}

	public void setBankRef(Integer bankRef) {
		this.bankRef = bankRef;
	}

	public Integer getBranchRef() {
		return branchRef;
	}

	public void setBranchRef(Integer branchRef) {
		this.branchRef = branchRef;
	}

	public List<Bank> getBanks() {
		return banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}

}
