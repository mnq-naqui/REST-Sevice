package com.enterprise.dto;

import java.io.Serializable;

public class BranchResource implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2065356028064409136L;
	private Integer branchId;
	private String branchName;
	private String ifscCode;
	private String branchCode;
	
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
}
