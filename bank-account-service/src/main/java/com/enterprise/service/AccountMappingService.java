package com.enterprise.service;

import com.enterprise.dto.UserDetailResource;

public interface AccountMappingService {
	
	public void createBankAccount(UserDetailResource customerDto, String accountDetailId);

}
