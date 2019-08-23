package com.enterprise.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.enterprise.BankAccountServiceApplicationTests;
import com.enterprise.dto.BankResource;
import com.enterprise.dto.BranchResource;
import com.enterprise.dto.UserDetailResource;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BankAccountControllerTest extends BankAccountServiceApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	private static final int id = 59;

	@Test
	public void testGetBankAccounts() throws Exception {
		mockMvc.perform(get("/bankaccount")).andExpect(status().isOk())
				.andExpect(content().contentType("application/hal+json;charset=UTF-8"))
				.andExpect(jsonPath("$..userId").exists()).andExpect(jsonPath("$..userName").exists())
				.andExpect(jsonPath("$..email").exists()).andExpect(jsonPath("$..mobile").exists())
				.andExpect(jsonPath("$..stateRef").exists()).andExpect(jsonPath("$..cityRef").exists())
				.andExpect(jsonPath("$..permanentAddress").exists()).andExpect(jsonPath("$..currentAddress").exists())
				.andExpect(jsonPath("$..cityRef").exists()).andExpect(jsonPath("$..pincode").exists())
				.andExpect(jsonPath("$.._links.bankaccounts").exists())
				.andExpect(jsonPath("$._links.self.href").exists()).andExpect(jsonPath("$.._links.self").exists())
				.andExpect(jsonPath("$._links.self.href").exists());

	}

	@Test
	public void testGetBankAccount() throws Exception {
		mockMvc.perform(get("/bankaccount/{id}", id)).andExpect(status().isOk())
				.andExpect(content().contentType("application/hal+json;charset=UTF-8"))
				.andExpect(jsonPath("$..userId").exists()).andExpect(jsonPath("$..userName").exists())
				.andExpect(jsonPath("$..email").exists()).andExpect(jsonPath("$..mobile").exists())
				.andExpect(jsonPath("$..stateRef").exists()).andExpect(jsonPath("$..cityRef").exists())
				.andExpect(jsonPath("$..permanentAddress").exists()).andExpect(jsonPath("$..currentAddress").exists())
				.andExpect(jsonPath("$..cityRef").exists()).andExpect(jsonPath("$..pincode").exists())
				.andExpect(jsonPath("$.._links.bankaccounts").exists())
				.andExpect(jsonPath("$._links.self.href").exists()).andExpect(jsonPath("$.._links.self").exists())
				.andExpect(jsonPath("$._links.self.href").exists()).andExpect(jsonPath("$.userId").value(id));

	}

	@Test
	public void testDeleteBankAccount() throws Exception {
		mockMvc.perform(delete("/bankaccount/{id}", id)).andExpect(status().isNoContent());

	}

	@Test
	public void testUpdateBankAccount() throws Exception {

		mockMvc.perform(put("/bankaccount/{id}", id).content(asJsonString(getUserResource()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$..userId").exists()).andExpect(jsonPath("$..userName").exists())
				.andExpect(jsonPath("$..email").exists()).andExpect(jsonPath("$..mobile").exists())
				.andExpect(jsonPath("$..stateRef").exists()).andExpect(jsonPath("$..cityRef").exists())
				.andExpect(jsonPath("$..permanentAddress").exists()).andExpect(jsonPath("$..currentAddress").exists())
				.andExpect(jsonPath("$..cityRef").exists()).andExpect(jsonPath("$..pincode").exists())
				.andExpect(jsonPath("$.._links.bankaccounts").exists())
				.andExpect(jsonPath("$._links.self.href").exists()).andExpect(jsonPath("$.._links.self").exists())
				.andExpect(jsonPath("$._links.self.href").exists()).andExpect(jsonPath("$.userId").value(id));

	}

	@Test
	public void testCreateBankAccount() throws Exception {

		mockMvc.perform(post("/bankaccount").content(asJsonString(getUserResource()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$..userId").exists()).andExpect(jsonPath("$..userName").exists())
				.andExpect(jsonPath("$..email").exists()).andExpect(jsonPath("$..mobile").exists())
				.andExpect(jsonPath("$..stateRef").exists()).andExpect(jsonPath("$..cityRef").exists())
				.andExpect(jsonPath("$..permanentAddress").exists()).andExpect(jsonPath("$..currentAddress").exists())
				.andExpect(jsonPath("$..cityRef").exists()).andExpect(jsonPath("$..pincode").exists())
				.andExpect(jsonPath("$.._links.bankaccounts").exists())
				.andExpect(jsonPath("$._links.self.href").exists()).andExpect(jsonPath("$.._links.self").exists())
				.andExpect(jsonPath("$._links.self.href").exists());

	}

	public static String asJsonString(final Object obj) {
		try {
			String json = new ObjectMapper().writeValueAsString(obj);
			System.out.println(json);
			return json;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static UserDetailResource getUserResource() {
		UserDetailResource resource = new UserDetailResource();
		resource.setUserName("Zabuza");
		resource.setEmail("zabuza.qureshi@evry.com");
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

		resource.setBanks(banks);

		return resource;
	}

}
