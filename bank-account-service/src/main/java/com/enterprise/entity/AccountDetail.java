package com.enterprise.entity;

import java.io.Serializable;

public class AccountDetail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1787496860788466127L;
	private Integer accountDetailId;
	private Double balance;
	private String accountNumber;
	private String accountType;
	
	public Integer getAccountDetailId() {
		return accountDetailId;
	}
	public void setAccountDetailId(Integer accountDetailId) {
		this.accountDetailId = accountDetailId;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
}
