package com.enterprise.test.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
	
	private static final String BOB_NAME = "BOb";
	private static final String BOB_EMAIL = "bob@test.com";
	private static final String BOB_MOBILE = "9934878776";
	private static final String BOB_CURRENT_ADD = "current address";
	private static final String BOB_PER_ADD = "permanent address";
	private static final String BOB_PINCODE = "2434225";
	private static final Integer BOB_BANK_REF = 1;
	private static final Integer BOB_BRANCH_REF = 2;
	private static final Integer BOB_CITY_REF = 1;
	private static final Integer BOB_STATE_REF = 2;

	private static final int ONE_CUSTOMER = 1;
	private static final int TWO_CUSTOMERS = 2;

	@Autowired
	private UserDetailDao userDetailDao;

	private UserDetail alice;
	
	private UserDetail bob; 

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
		
		bob = new UserDetail();

		bob.setUserName(BOB_NAME);
		bob.setEmail(BOB_EMAIL);
		bob.setMobile(BOB_MOBILE);
		bob.setCurrentAddress(BOB_CURRENT_ADD);
		bob.setPermanentAddress(BOB_PER_ADD);
		bob.setPincode(BOB_PINCODE);
		bob.setBankRef(BOB_BANK_REF);
		bob.setBranchRef(BOB_BRANCH_REF);
		bob.setCityRef(BOB_CITY_REF);
		bob.setStateRef(BOB_STATE_REF);

	}
	
	@Test
    public void findAll_shouldYieldEmptyList_forEmptyDatabase() {
        List<UserDetail> noCustomers = userDetailDao.findAll();
        assertThat(noCustomers).isNullOrEmpty();
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
    public void findAll_shouldYieldListOfCustomers_forNonemptyDatabase() {

        userDetailDao.create(alice);
        List<UserDetail> customers = userDetailDao.findAll();

        assertThat(customers).isNotNull().hasSize(ONE_CUSTOMER);

        UserDetail result = customers.get(0);

		assertThat(result).hasFieldOrPropertyWithValue("userName", ALICE_NAME);
		assertThat(result).hasFieldOrPropertyWithValue("email", ALICE_EMAIL);
		assertThat(result).hasFieldOrPropertyWithValue("mobile", ALICE_MOBILE);
		assertThat(result).hasFieldOrPropertyWithValue("currentAddress", ALICE_CURRENT_ADD);
		assertThat(result).hasFieldOrPropertyWithValue("permanentAddress", ALICE_PER_ADD);
		assertThat(result).hasFieldOrPropertyWithValue("pincode", ALICE_PINCODE);
		assertThat(result).hasFieldOrPropertyWithValue("bankRef", ALICE_BANK_REF);
		assertThat(result).hasFieldOrPropertyWithValue("branchRef", ALICE_BRANCH_REF);
		assertThat(result).hasFieldOrPropertyWithValue("cityRef", ALICE_CITY_REF);
		assertThat(result).hasFieldOrPropertyWithValue("stateRef", ALICE_STATE_REF);

        userDetailDao.create(bob);
        customers = userDetailDao.findAll();

        assertThat(customers).isNotNull().hasSize(TWO_CUSTOMERS);
    }

	@Test
	public void findById_shouldReturnInvalidCustomer_forEmptyRow() {
		Optional<UserDetail> invalidCustomer = userDetailDao.findById(new Random().nextInt());
		assertThat(invalidCustomer.isPresent()).isFalse();
	}
	

}
