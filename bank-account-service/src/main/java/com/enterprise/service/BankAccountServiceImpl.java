package com.enterprise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.dao.AccountDetailDao;
import com.enterprise.dao.AccountMappingDao;
import com.enterprise.dao.UserDetailDao;
import com.enterprise.dto.UserDetailResource;
import com.enterprise.entity.AccountMapping;
import com.enterprise.entity.UserDetail;
import com.enterprise.exception.UserNotFoundException;

@Service
public class BankAccountServiceImpl implements BankAccountService {
	
	@Autowired
	UserDetailDao customerDao;
	
	@Autowired
	AccountMappingDao accMappingDao;
	
	@Autowired
	AccountDetailDao accDetailDao;
	
	@Override
	public UserDetail createBankAccount(UserDetailResource customerDto) {
		
		UserDetail customer=new UserDetail();
		BeanUtils.copyProperties(customerDto, customer);
		return customerDao.create(customer);
	}

	@Override
	public UserDetail updateBankAccount(UserDetailResource customerDto) {
		
		UserDetail customer = customerDao.findById((customerDto.getUserId())).orElseThrow(() -> new UserNotFoundException(UserDetail.class, "id", customerDto.getUserId().toString()));
		BeanUtils.copyProperties(customerDto, customer);
		customerDao.update(customer) ;
		return customer;
	}

	@Override
	public Boolean deleteBankAccount(int customerId) {
		
		accDetailDao.delete(
				accMappingDao.getMappingByUserId(customerId)
					.stream().findAny().get().getAccountDetailRef());
		accMappingDao.delete(customerId);
		
		return customerDao.delete(customerId);
	}

	@Override
	public UserDetail getBankAccount(int customerId) {
		
		return customerDao.findById(customerId).orElseThrow(() -> new UserNotFoundException(UserDetail.class, "id", Integer.toString(customerId)));
	}

	@Override
	public List<UserDetail> getBankAccounts() {
		return customerDao.findAll();
	}

}
