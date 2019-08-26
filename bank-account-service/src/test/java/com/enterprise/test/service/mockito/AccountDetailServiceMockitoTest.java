package com.enterprise.test.service.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.enterprise.dao.AccountDetailDaoImpl;
import com.enterprise.entity.AccountDetail;
import com.enterprise.service.AccountDetailServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AccountDetailServiceMockitoTest {
	

	@Mock
	private AccountDetailDaoImpl accDetailDao;

	@InjectMocks
	private AccountDetailServiceImpl accService;

	@Captor
	ArgumentCaptor<String> captor;
	
	@Test
	public void whenCreateAccountDetail_thenReturnAccountDetail() {
		// given
		String accountNumber = "89778781";
		AccountDetail accDetail = new AccountDetail(1, 0.0D, accountNumber, "Savings");

		doReturn(accDetail).when(accDetailDao).createAccountDetail(any(String.class));

		// when
		AccountDetail actualResult = accService.createAccountDetail();

		// then
		assertThat(actualResult.getAccountNumber()).isEqualTo(accDetail.getAccountNumber());
		verify(accDetailDao, times(1)).createAccountDetail(captor.capture());
	}

}
