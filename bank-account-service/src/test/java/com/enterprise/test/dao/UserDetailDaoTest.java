package com.enterprise.test.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.enterprise.BankAccountServiceApplicationTests;
import com.enterprise.dao.UserDetailDao;
import com.enterprise.entity.UserDetail;

public class UserDetailDaoTest extends BankAccountServiceApplicationTests {

	private static final String ALICE_NAME = "Alice";
	private static final String ALICE_EMAIL = "alice@test.com";
	private static final String ALICE_MOBILE = "9800878776";
	private static final String ALICE_CURRENT_ADD = "current address";
	private static final String ALICE_PER_ADD = "permanent address";
	private static final String ALICE_PINCODE = "2434225";
	private static final Integer ALICE_BANK_REF = 1;
	private static final Integer ALICE_BRANCH_REF = 2;
	private static final Integer ALICE_CITY_REF = 1;
	private static final Integer ALICE_STATE_REF = 2;

	@Autowired
	private UserDetailDao userDetailDao;

	private UserDetail alice;

	@Before
	public void setUp() {
		alice = new UserDetail();
		alice.setUserName(ALICE_NAME);
		alice.setEmail(ALICE_EMAIL);
		alice.setMobile(ALICE_MOBILE);
		alice.setCurrentAddress(ALICE_CURRENT_ADD);
		alice.setPermanentAddress(ALICE_PER_ADD);
		alice.setPincode(ALICE_PINCODE);
		alice.setBankRef(ALICE_BANK_REF);
		alice.setBranchRef(ALICE_BRANCH_REF);
		alice.setCityRef(ALICE_CITY_REF);
		alice.setStateRef(ALICE_STATE_REF);

	}
	
	
	@After
	public void cleanUp() {
		userDetailDao.delete(alice.getUserId());
	}
	
	@Test
	public void update_shouldYieldFalse_forEmptyDatabase() {
		UserDetail notFound = new UserDetail();
		notFound.setUserId(new Random().nextInt());
		assertThat(userDetailDao.update(notFound)).isFalse();
	}

	@Test
	public void findById_shouldReturnInvalidCustomer_forEmptyRow() {
		Optional<UserDetail> invalidCustomer = userDetailDao.findById(new Random().nextInt());
		assertThat(invalidCustomer.isPresent()).isFalse();
	}

	@Test
	public void delete_shouldYieldFalse_forEmptyDatabaseOrNonexistingCustomer() {
		assertThat(userDetailDao.delete(new Random().nextInt())).isFalse();
	}

	@Test
	public void create_shouldReturnValidCustomer_whenAddingNewCustomer() {

		userDetailDao.create(alice);

		assertThat(alice.getUserId()).isNotNull();

		Optional<UserDetail> result = userDetailDao.findById(alice.getUserId());

		assertThat(result).isPresent();

		assertThat(alice).hasFieldOrPropertyWithValue("userName", ALICE_NAME);
		assertThat(alice).hasFieldOrPropertyWithValue("email", ALICE_EMAIL);
		assertThat(alice).hasFieldOrPropertyWithValue("mobile", ALICE_MOBILE);
		assertThat(alice).hasFieldOrPropertyWithValue("currentAddress", ALICE_CURRENT_ADD);
		assertThat(alice).hasFieldOrPropertyWithValue("permanentAddress", ALICE_PER_ADD);
		assertThat(alice).hasFieldOrPropertyWithValue("pincode", ALICE_PINCODE);
		assertThat(alice).hasFieldOrPropertyWithValue("bankRef", ALICE_BANK_REF);
		assertThat(alice).hasFieldOrPropertyWithValue("branchRef", ALICE_BRANCH_REF);
		assertThat(alice).hasFieldOrPropertyWithValue("cityRef", ALICE_CITY_REF);
		assertThat(alice).hasFieldOrPropertyWithValue("stateRef", ALICE_STATE_REF);

	}

	 @Test
	    public void delete_shouldYieldTrue_forExistingCustomer() {
		    userDetailDao.create(alice);
	        assertThat(userDetailDao.delete(alice.getUserId())).isTrue();
	        assertThat(userDetailDao.findById(alice.getUserId()).isPresent()).isFalse();
	    }

}
