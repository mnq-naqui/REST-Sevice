package com.enterprise.test.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.enterprise.controller.BankAccountController;
import com.enterprise.controller.BankController;
import com.enterprise.dto.BankResource;
import com.enterprise.dto.BranchResource;
import com.enterprise.dto.UserDetailResource;
import com.enterprise.entity.UserDetail;
import com.enterprise.test.URL;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(BankAccountController.class)
public class BankAccountControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BankAccountController bankController;

	@Test
	public void testGetBankAccounts() throws Exception {

		UserDetailResource userResource = getUserResource();

		Link bankAccountsLink = new Link(URL.BASE_PATH + URL.GET_BANKACCOUNT).withRel("bankaccounts");
		Link banksLink = new Link(URL.BASE_PATH + URL.GET_BANKACCOUNT + "/" + userResource.getUserId() + "/banks")
				.withRel("banks");
		Link selfLink = new Link(URL.BASE_PATH + URL.GET_BANKACCOUNT + "/" + userResource.getUserId()).withSelfRel();

		userResource.add(bankAccountsLink);
		userResource.add(banksLink);
		userResource.add(selfLink);

		List<UserDetailResource> list = Arrays.asList(userResource);
		final Resources<UserDetailResource> resources = new Resources<>(list);

		resources.add(new Link(URL.BASE_PATH + URL.GET_BANKACCOUNT, "self"));
		ResponseEntity<Resources<UserDetailResource>> expectedList = ResponseEntity.ok(resources);

		given(bankController.getBankAccounts()).willReturn(expectedList);

		// asJsonString(expectedList);

		mockMvc.perform(get(URL.GET_BANKACCOUNT)).andExpect(status().isOk())
				.andExpect(content().contentType("application/hal+json;charset=UTF-8"))
				.andExpect(jsonPath("$..userDetailResourceList", hasSize(1)))
				.andExpect(jsonPath("$..userId").value(userResource.getUserId()))
				.andExpect(jsonPath("$..userName").value(userResource.getUserName()))
				.andExpect(jsonPath("$..email").value(userResource.getEmail()))
				.andExpect(jsonPath("$..mobile").value(userResource.getMobile()))
				.andExpect(jsonPath("$..stateRef").value(userResource.getStateRef()))
				.andExpect(jsonPath("$..cityRef").value(userResource.getCityRef()))
				.andExpect(jsonPath("$..permanentAddress").value(userResource.getPermanentAddress()))
				.andExpect(jsonPath("$..currentAddress").value(userResource.getCurrentAddress()))
				.andExpect(jsonPath("$..pincode").value(userResource.getPincode()))
				.andExpect(jsonPath("$.._links.bankaccounts.href").value(bankAccountsLink.getHref()))
				.andExpect(jsonPath("$.._links.banks.href").value(banksLink.getHref()))
				.andExpect(jsonPath("$.._links.self").exists());
		// .andExpect(jsonPath("$.._links.self").exists()).andExpect(jsonPath("$.._links.self.href").value(selfLink.getHref()));

	}

	@Test
	public void testGetBankAccount() throws Exception {

		UserDetailResource userDetailResource = getUserResource();

		Link bankAccountsLink = new Link(URL.BASE_PATH + URL.GET_BANKACCOUNT_BY_ID).withRel("bankaccounts");
		Link banksLink = new Link(URL.BASE_PATH + URL.GET_BANKACCOUNT_BY_ID + "/" + userDetailResource.getUserId() + "/banks")
				.withRel("banks");
		Link selfLink = new Link(URL.BASE_PATH + URL.GET_BANKACCOUNT_BY_ID + "/" + userDetailResource.getUserId())
				.withSelfRel();

		userDetailResource.add(bankAccountsLink);
		userDetailResource.add(banksLink);
		userDetailResource.add(selfLink);

		ResponseEntity<UserDetailResource> exp = ResponseEntity.ok(userDetailResource);

		given(bankController.getBankAccount(any(Integer.class))).willReturn(exp);

		mockMvc.perform(get(URL.GET_BANKACCOUNT_BY_ID, any(Integer.class))).andExpect(status().isOk())
				.andExpect(content().contentType("application/hal+json;charset=UTF-8"))
				.andExpect(jsonPath("$..userId").value(userDetailResource.getUserId()))
				.andExpect(jsonPath("$..userName").value(userDetailResource.getUserName()))
				.andExpect(jsonPath("$..email").value(userDetailResource.getEmail()))
				.andExpect(jsonPath("$..mobile").value(userDetailResource.getMobile()))
				.andExpect(jsonPath("$..stateRef").value(userDetailResource.getStateRef()))
				.andExpect(jsonPath("$..cityRef").value(userDetailResource.getCityRef()))
				.andExpect(jsonPath("$..permanentAddress").value(userDetailResource.getPermanentAddress()))
				.andExpect(jsonPath("$..currentAddress").value(userDetailResource.getCurrentAddress()))
				.andExpect(jsonPath("$..pincode").value(userDetailResource.getPincode()))
				.andExpect(jsonPath("$.._links.bankaccounts.href").value(bankAccountsLink.getHref()))
				.andExpect(jsonPath("$.._links.banks.href").value(banksLink.getHref()))
				.andExpect(jsonPath("$.._links.self").exists());

	}

	public static UserDetail getUserDetail() {

		return new UserDetail(1, "Naqui Qureshi", "naqui.qureshi@evry.com", "900842788", "HSR Bangalore", "Chhindwara",
				1, 2, "560101");
	}

	public static UserDetailResource getUserResource() {
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

		return resource;
	}

	public static String asJsonString(final Object obj) {
		try {
			String json = new ObjectMapper().writeValueAsString(obj);
			// System.out.println(json);
			return json;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		Link banksLink = new Link(URL.BASE_PATH + URL.GET_BANKACCOUNT + "/" + 1 + "/").withRel("banks");
		System.out.println(banksLink.getHref());
	}

}
