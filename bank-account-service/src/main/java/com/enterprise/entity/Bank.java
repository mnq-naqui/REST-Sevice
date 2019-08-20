package com.enterprise.entity;

import java.io.Serializable;
import java.util.List;

public class Bank implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6715940725293346739L;
	
	private Integer bankId;
	private String bankName;
	private String bankCode;
	private String ifscCode;
	private String address;
	private Integer cityRef;
	private Integer stateRef;
	private String pincode;
	private List<Branch> branches;
	
	public Integer getBankId() {
		return bankId;
	}
	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public List<Branch> getBranches() {
		return branches;
	}
	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	
}
