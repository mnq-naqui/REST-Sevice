package com.enterprise.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.dao.AccountMappingDao;
import com.enterprise.dto.BankResource;
import com.enterprise.dto.UserDetailResource;

@Service
public class AccountMappingServiceImpl implements AccountMappingService {
	
	@Autowired
	AccountMappingDao maapingDao;

	@Override
	public void createBankAccount(UserDetailResource customerDto, Integer accountDetailId) {
		
		List<BankResource> banks = customerDto.getBanks();
		
		for (BankResource bankResource : banks) {
			
			String branchIds = bankResource.getBranches().stream()
					.map(x -> String.valueOf(x.getBranchId())).collect(Collectors.joining(","));
			maapingDao.creatMapping(bankResource.getBankId(), branchIds, accountDetailId, customerDto.getUserId());
		}
		
	}
	
	

}
