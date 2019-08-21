package com.enterprise.dao;

import com.enterprise.entity.AccountDetail;

public interface AccountDetailDao {
	
	public AccountDetail createAccountDetail(String accountNumber);

	boolean delete(Integer id);

}
