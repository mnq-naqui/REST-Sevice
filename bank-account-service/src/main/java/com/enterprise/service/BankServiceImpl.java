package com.enterprise.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.dao.AccountMappingDao;
import com.enterprise.dao.BankDao;
import com.enterprise.dao.UserDetailDao;
import com.enterprise.entity.Bank;
import com.enterprise.entity.UserDetail;
import com.enterprise.exception.UserNotFoundException;

@Service
public class BankServiceImpl implements BankService {
	
	@Autowired
	BankDao bankDao;
	
	@Autowired
	AccountMappingDao accountMappingDao;
	
	@Autowired
	UserDetailDao userDetailDao;
	

	@Override
	public List<Bank> getBankForUser(Integer userId) {
		
		userDetailDao.findById(userId).orElseThrow(() -> new UserNotFoundException(UserDetail.class, "id", Integer.toString(userId)));
		List<String> bankIds = accountMappingDao.getMappingByUserId(userId).stream()
				.map(x -> String.valueOf(x.getBankRef())).distinct().collect(Collectors.toList());
		
		
		return bankDao.findBankForUser(bankIds);
	}

}
