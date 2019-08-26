package com.enterprise.test.service.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;

import static org.mockito.Mockito.doNothing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.enterprise.dao.AccountMappingDaoImpl;
import com.enterprise.dto.BankResource;
import com.enterprise.dto.BranchResource;
import com.enterprise.dto.UserDetailResource;
import com.enterprise.service.AccountMappingServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AccountMappingServiceMockitoTest {
	
	@Mock
	AccountMappingDaoImpl mockedMappingDao;
	
	@InjectMocks
	AccountMappingServiceImpl mockedAccMappingService;
	
	@Test
	public void whenCreateBankAccount_thenReturnUserDetail() {
		// given
		UserDetailResource resource = new UserDetailResource();

		resource.setUserId(1);
		resource.setUserName("Zabuza");
		resource.setEmail("zabuza@evry.com");
		resource.setMobile("909365188");
		resource.setCurrentAddress("HSR Bangalore");
		resource.setPermanentAddress("China");
		resource.setCityRef(1);
		resource.setStateRef(2);
		resource.setPincode("560101");

		ArrayList<BankResource> banks = new ArrayList<>();
		ArrayList<BranchResource> branches = new ArrayList<>();

		BankResource bank = new BankResource();

		BranchResource branchResource = new BranchResource();
		branchResource.setBranchId(1);
		bank.setBankId(1);
		bank.setBranches(branches);

		branches.add(branchResource);
		banks.add(bank);
		
		 doNothing().when(mockedMappingDao).creatMapping( any(Integer.class),  any(String.class),  any(Integer.class),  any(Integer.class));
		 mockedAccMappingService.createBankAccount(any(UserDetailResource.class),  any(Integer.class));
		 
	}
}
