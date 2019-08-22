package com.enterprise.test.service.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.enterprise.dao.AccountMappingDaoImpl;
import com.enterprise.dao.BankDaoImpl;
import com.enterprise.dao.UserDetailDaoImpl;
import com.enterprise.entity.AccountMapping;
import com.enterprise.entity.Bank;
import com.enterprise.entity.UserDetail;
import com.enterprise.service.BankServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BankServiceMockitoTest {

	@Mock
	BankDaoImpl bankDao;

	@Mock
	AccountMappingDaoImpl accountMappingDao;

	@Mock
	UserDetailDaoImpl userDetailDao;

	@InjectMocks
	BankServiceImpl bankService;

	@Test
	public void whenGetBankForUser_thenBankListForUser() {
		// given
		List<Bank> expectedBanks = Arrays.asList(new Bank(1, "SBI", "SBI-IN", "IFSC-SBI", "SBI India", 1, 2, "90098"),
				new Bank(1, "AXIS", "AXIS-IN", "IFSC-AXIS", "AXIS India", 1, 2, "90598"));

		UserDetail toUpdate = new UserDetail(1, "Naqui Qureshi", "naqui.qureshi@evry.com", "900842788", "HSR Bangalore",
				"Chhindwara", 1, 2, "560101");
		Optional<UserDetail> exp1 = Optional.of(toUpdate);
		
		List<AccountMapping> exp3=  Arrays.asList(new AccountMapping(1, 1, "1,1", 1),
				  new AccountMapping(1, 1, "1,1", 1));
		
		List<String> exp2 = new ArrayList<>();
		exp2.add("1");
		exp2.add("2");

		doReturn(exp1).when(userDetailDao).findById(1);
		doReturn(exp3).when(accountMappingDao).getMappingByUserId(1);
		doReturn(expectedBanks).when(bankDao).findBankForUser(exp2);

		// when
		List<Bank> actualResult = bankService.getBankForUser(1);

		// then
		assertThat(actualResult).isEqualTo(expectedBanks);
	}

}
