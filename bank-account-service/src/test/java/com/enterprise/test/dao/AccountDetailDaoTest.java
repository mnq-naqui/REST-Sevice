package com.enterprise.test.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.enterprise.BankAccountServiceApplicationTests;
import com.enterprise.dao.AccountDetailDao;
import com.enterprise.entity.AccountDetail;

public class AccountDetailDaoTest extends BankAccountServiceApplicationTests {

	private static final String ACC_NUMBER = "12339022";
	private static final String ACC_TYPE = "Savings";
	private static final Double BAL = 0.0D;

	@Autowired
	private AccountDetailDao accDetailDao;

	private AccountDetail accDetail;

	@Before
	public void setUp() {
		accDetail = new AccountDetail();
		accDetail.setAccountNumber(ACC_NUMBER);
		accDetail.setAccountType(ACC_TYPE);
		accDetail.setBalance(BAL);

	}

	@After
	public void cleanUp() {
		accDetailDao.delete((accDetail.getAccountDetailId()));
	}

	@Test
	public void delete_shouldYieldFalse_forEmptyDatabaseOrNonexistingCustomer() {
		assertThat(accDetailDao.delete(new Random().nextInt())).isFalse();
	}

	@Test
	public void create_shouldReturnValidCustomer_whenAddingNewCustomer() {

		AccountDetail acc = accDetailDao.createAccountDetail(accDetail.getAccountNumber());

		assertThat(acc.getAccountDetailId()).isNotNull();

		assertThat(accDetail).hasFieldOrPropertyWithValue("accountNumber", ACC_NUMBER);
		assertThat(accDetail).hasFieldOrPropertyWithValue("accountType", ACC_TYPE);
		assertThat(accDetail).hasFieldOrPropertyWithValue("balance", BAL);
	}

	@Test
	public void delete_shouldYieldTrue_forExistingCustomer() {
		AccountDetail acc = accDetailDao.createAccountDetail(accDetail.getAccountNumber());
		assertThat(accDetailDao.delete((acc.getAccountDetailId()))).isTrue();
	}

}
