package com.enterprise.service;

import java.util.List;

import com.enterprise.entity.Bank;

public interface BankService {
	
	public List<Bank> getBankForUser(Integer userId);

}
