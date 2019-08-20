package com.enterprise.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.dao.AccountDetailDao;
import com.enterprise.entity.AccountDetail;

@Service
public class AccountDetailServiceImpl implements AccountDetailService {
	
	@Autowired
	AccountDetailDao accDetailDao;

	@Override
	public AccountDetail createAccountDetail() {
		
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);
		n=n-rnd.nextInt(1358);
		
		return accDetailDao.createAccountDetail(Integer.toString(n));
	}

}
