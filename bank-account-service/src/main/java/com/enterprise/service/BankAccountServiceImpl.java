package com.enterprise.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.dao.CustomerDao;
import com.enterprise.dto.UserDetailResource;
import com.enterprise.entity.UserDetail;
import com.enterprise.exception.CustomerNotFound;

@Service
public class BankAccountServiceImpl implements BankAccountService {
	
	@Autowired
	CustomerDao customerDao;
	
	@Override
	public UserDetail createBankAccount(UserDetailResource customerDto) {
		
		UserDetail customer=new UserDetail();
		BeanUtils.copyProperties(customerDto, customer);
		
		return customerDao.create(customer);
	}

	@Override
	public UserDetail updateBankAccount(UserDetailResource customerDto) {
		
		//UserDetail customer = customerDao.findById(Integer.parseInt(customerDto.getCustomerId())).orElseThrow(CustomerNotFound :: new);
		UserDetail customer = null;
		BeanUtils.copyProperties(customerDto, customer);
		customerDao.update(customer) ;
		
		return customer;
	}

	@Override
	public Boolean deleteBankAccount(int customerId) {
		
		return customerDao.delete(customerId);
	}

	@Override
	public UserDetail getBankAccount(int customerId) {
		
		return customerDao.findById(customerId).orElseThrow(CustomerNotFound :: new);
	}

	@Override
	public List<UserDetail> getBankAccounts() {
		return customerDao.findAll();
	}

}
