package com.enterprise.test.service.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.enterprise.dao.AccountMappingDaoImpl;
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
		UserDetailResource resource=new UserDetailResource();
		resource.setUserName("Naqui Qureshi");
		resource.setPermanentAddress("Chhindwara");
		resource.setEmail("naqui.qureshi@evry.com");
		resource.setMobile("900842788");
		resource.setCurrentAddress("HSR Bangalore");
		resource.setCityRef(1);
		resource.setStateRef(2);
		resource.setPincode("560101");
		
		Mockito.doThrow(new RuntimeException()).when(mockedAccMappingService).createBankAccount(resource, 1);
		
	}
}
