package com.enterprise.service;

import java.util.List;

import com.enterprise.dto.UserDetailResource;
import com.enterprise.entity.UserDetail;

public interface BankAccountService {
	
	public UserDetail createBankAccount(UserDetailResource customerDto);
	
	public UserDetail updateBankAccount(UserDetailResource customerDto);
	
	public Boolean deleteBankAccount(int customerId);
	
	public UserDetail getBankAccount(int customerId);
	
	public List<UserDetail> getBankAccounts();

}
