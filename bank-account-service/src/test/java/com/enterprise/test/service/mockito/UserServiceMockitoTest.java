package com.enterprise.test.service.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.enterprise.dao.AccountDetailDaoImpl;
import com.enterprise.dao.AccountMappingDaoImpl;
import com.enterprise.dao.UserDetailDaoImpl;
import com.enterprise.dto.UserDetailResource;
import com.enterprise.entity.AccountMapping;
import com.enterprise.entity.UserDetail;
import com.enterprise.service.BankAccountServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceMockitoTest {

	@Mock(name="userDao")
	private UserDetailDaoImpl userDao;
	
	@Mock(name="accDetailDao")
	private AccountDetailDaoImpl accDetailDao;
	
	@Mock(name="accMappingDao")
	private AccountMappingDaoImpl accMappingDao;

	@InjectMocks
	private BankAccountServiceImpl userService;
	

	@Test
	public void whenGetBankAccounts_thenReturnUserDetailList() {
		// given
		List<UserDetail> expectedUsers = Arrays.asList(new UserDetail(1, "Naqui Qureshi", "naqui.qureshi@evry.com", "900842788", "HSR Bangalore", "Chhindwara", 1, 2, "560101"),
				new UserDetail(1, "Alice", "alice@evry.com", "900842278", "HSR Bangalore", "Wonderland", 1, 2, "560109")
				);

		doReturn(expectedUsers).when(userDao).findAll();

		// when
		List<UserDetail> actualUsers = userService.getBankAccounts();

		// then
		assertThat(actualUsers).isEqualTo(expectedUsers);
	}
	
	@Test
	public void whenGetBankAccount_thenReturnUserDetail() {
		// given
		UserDetail ud = new UserDetail(1, "Naqui Qureshi", "naqui.qureshi@evry.com", "900842788", "HSR Bangalore", "Chhindwara", 1, 2, "560101");
		Optional<UserDetail> expectedUser = Optional.of(ud);

		doReturn(expectedUser).when(userDao).findById(1);

		// when
		UserDetail actualUser = userService.getBankAccount(1);

		// then
		assertThat(actualUser).isEqualTo(expectedUser.get());
	}
	
	@Test
	public void whenDeleteBankAccount_thenReturnBoolean() {
		// given
		Boolean expectedResult=true;
		List<AccountMapping> exp1=  Arrays.asList(new AccountMapping(1, 1, "1,1", 1),
												  new AccountMapping(1, 1, "1,1", 1));
		
		doReturn(expectedResult).when(userDao).delete(1);
		doReturn(exp1).when(accMappingDao).getMappingByUserId(1);

		// when
		Boolean actualResult = userService.deleteBankAccount(1);

		// then
		assertThat(actualResult).isEqualTo(expectedResult);
	}
	
	
	@Test
	public void whenUpdateBankAccount_thenReturnUpdatedUserDetail() {
		// given
		UserDetailResource resource=new UserDetailResource();
		resource.setUserId(1);
		resource.setUserName("Mohd Naqui Qureshi");
		resource.setPermanentAddress("Chhindwara MP");
		
		Boolean expectedRes=true;
		UserDetail toUpdate = new UserDetail(1, "Naqui Qureshi", "naqui.qureshi@evry.com", "900842788", "HSR Bangalore", "Chhindwara", 1, 2, "560101");
		UserDetail expectedResult = new UserDetail(1, "Mohd Naqui Qureshi", "naqui.qureshi@evry.com", "900842788", "HSR Bangalore", "Chhindwara MP", 1, 2, "560101");
		
		Optional<UserDetail> toUpdateOptional = Optional.of(toUpdate);
		
		doReturn(toUpdateOptional).when(userDao).findById(1);
		doReturn(expectedRes).when(userDao).update(toUpdate);

		// when
		UserDetail actualResult = userService.updateBankAccount(resource);

		// then
		assertThat(actualResult.getPermanentAddress()).isEqualTo(expectedResult.getPermanentAddress());
		assertThat(actualResult.getUserName()).isEqualTo(expectedResult.getUserName());
	}
	
	/*@Test
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
		
		UserDetail toUpdate = new UserDetail(1, "Naqui Qureshi", "naqui.qureshi@evry.com", "900842788", "HSR Bangalore", "Chhindwara", 1, 2, "560101");
		UserDetail expectedResult = new UserDetail(1, "Mohd Naqui Qureshi", "naqui.qureshi@evry.com", "900842788", "HSR Bangalore", "Chhindwara MP", 1, 2, "560101");
		
		doReturn(expectedResult).when(userDao).create(toUpdate);

		// when
		UserDetail actualResult = userService.createBankAccount(resource);

		// then
		assertThat(actualResult.getPermanentAddress()).isEqualTo(expectedResult.getPermanentAddress());
		assertThat(actualResult.getUserName()).isEqualTo(expectedResult.getUserName());
	}*/

}
