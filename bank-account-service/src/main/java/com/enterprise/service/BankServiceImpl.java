package com.enterprise.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.dao.AccountMappingDao;
import com.enterprise.dao.BankDao;
import com.enterprise.entity.AccountMapping;
import com.enterprise.entity.Bank;

@Service
public class BankServiceImpl implements BankService {
	
	@Autowired
	BankDao bankDao;
	
	@Autowired
	AccountMappingDao accountMappingDao;
	

	@Override
	public List<Bank> getBankForUser(Integer userId) {
		
		/*String bankIds = accountMappingDao.getMappingByUserId(userId).stream()
		.map(x -> String.valueOf(x.getBankRef())).distinct().collect(Collectors.joining(","));
*/		
		List<String> bankIds = accountMappingDao.getMappingByUserId(userId).stream()
				.map(x -> String.valueOf(x.getBankRef())).distinct().collect(Collectors.toList());
		
		return bankDao.findBankForUser(bankIds);
	}

}
