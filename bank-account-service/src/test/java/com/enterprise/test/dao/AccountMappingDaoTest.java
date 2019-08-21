package com.enterprise.test.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.enterprise.BankAccountServiceApplicationTests;
import com.enterprise.dao.AccountMappingDao;
import com.enterprise.entity.AccountMapping;

public class AccountMappingDaoTest extends BankAccountServiceApplicationTests {

	private static final Integer ACC_DETAIL_REF = 3;
	private static final Integer BANK_REF = 1;
	private static final String BRANCH_REF = "1,2";
	private static final Integer USER_REF = 3;

	@Autowired
	private AccountMappingDao accMappingDao;

	private AccountMapping accMapping;

	@Before
	public void setUp() {
		accMapping = new AccountMapping();
		accMapping.setAccountDetailRef(ACC_DETAIL_REF);
		accMapping.setBankRef(BANK_REF);
		accMapping.setBranchRef(BRANCH_REF);
		accMapping.setUserRef(USER_REF);
	}

	@After
	public void cleanUp() {
		accMappingDao.delete(accMapping.getUserRef());
	}

	@Test
	public void findById_shouldReturnInvalidCustomer_forEmptyRow() {
		List<AccountMapping> invalidCustomer = accMappingDao.getMappingByUserId(new Random().nextInt());
		for (AccountMapping accountMapping : invalidCustomer) {
			assertThat(accountMapping.getAccountDetailRef()).isNull();
		}

	}

	@Test
	public void delete_shouldYieldFalse_forEmptyDatabaseOrNonexistingCustomer() {
		assertThat(accMappingDao.delete(new Random().nextInt())).isFalse();
	}

	@Test
	public void create_shouldReturnValidCustomer_whenAddingNewCustomer() {

		accMappingDao.creatMapping(BANK_REF, BRANCH_REF, ACC_DETAIL_REF, USER_REF);

		assertThat(accMapping.getAccountDetailRef()).isNotNull();

		List<AccountMapping> results = accMappingDao.getMappingByUserId(accMapping.getUserRef());
		for (AccountMapping accountMapping : results) {
			assertThat(accountMapping.getAccountDetailRef()).isNotNull();
		}

		assertThat(accMapping).hasFieldOrPropertyWithValue("accountDetailRef", ACC_DETAIL_REF);
		assertThat(accMapping).hasFieldOrPropertyWithValue("bankRef", BANK_REF);
		assertThat(accMapping).hasFieldOrPropertyWithValue("branchRef", BRANCH_REF);
		assertThat(accMapping).hasFieldOrPropertyWithValue("userRef", USER_REF);

	}

	@Test
	public void delete_shouldYieldTrue_forExistingCustomer() {
		accMappingDao.creatMapping(BANK_REF, BRANCH_REF, ACC_DETAIL_REF, USER_REF);
		assertThat(accMappingDao.delete(accMapping.getAccountDetailRef())).isTrue();
	}

}
