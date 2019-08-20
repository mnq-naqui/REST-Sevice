package com.enterprise.entity;

import java.io.Serializable;

public class AccountMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6187918936381500076L;
	
	private Integer accountDetailRef;
	
	private Integer bankRef;
	
	private String branchRef;
	
	private Integer userRef;

	public Integer getAccountDetailRef() {
		return accountDetailRef;
	}

	public void setAccountDetailRef(Integer accountDetailRef) {
		this.accountDetailRef = accountDetailRef;
	}

	public Integer getBankRef() {
		return bankRef;
	}

	public void setBankRef(Integer bankRef) {
		this.bankRef = bankRef;
	}

	public String getBranchRef() {
		return branchRef;
	}

	public void setBranchRef(String branchRef) {
		this.branchRef = branchRef;
	}

	public Integer getUserRef() {
		return userRef;
	}

	public void setUserRef(Integer userRef) {
		this.userRef = userRef;
	}
	
}
