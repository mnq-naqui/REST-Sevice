package com.enterprise.dto;

import java.io.Serializable;
import java.util.List;

public class BankResource implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6715940725293346739L;
	
	private Integer bankId;
	private String bankName;
	private String ifscCode;
	private String bankCode;
	private List<BranchResource> branches;
	
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
	public List<BranchResource> getBranches() {
		return branches;
	}
	public void setBranches(List<BranchResource> branches) {
		this.branches = branches;
	}
	
}
