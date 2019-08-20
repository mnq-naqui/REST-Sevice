package com.enterprise.dao;

import java.util.List;

import com.enterprise.entity.Bank;

public interface BankDao {
	
	List<Bank> findBankForUser(List<String> bankIds);
	

}
