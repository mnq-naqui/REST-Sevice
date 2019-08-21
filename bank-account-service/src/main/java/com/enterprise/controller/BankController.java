package com.enterprise.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.enterprise.dto.BankResource;
import com.enterprise.entity.Bank;
import com.enterprise.service.BankAccountService;
import com.enterprise.service.BankService;


@RestController
@RequestMapping(path="/bankaccount/{customerId}/bank") 
public class BankController {
	
	@Autowired
	BankService bankService;
	
	@Autowired
	BankAccountService bankAccountService;
	
	@GetMapping
	public ResponseEntity<Resources<BankResource>> getBanksForUser(@PathVariable final int customerId) {
		
		List<Bank> collection = bankService.getBankForUser(customerId);
		List<BankResource> brList=new ArrayList<>();
		
		for (Bank bank : collection) {
			
			BankResource br =new BankResource();
			BeanUtils.copyProperties(bank, br);
			brList.add(br);
		}
		
		
	    final Resources<BankResource> resources = new Resources<>(brList);
	    final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
	    resources.add(new Link(uriString, "self"));
	    return ResponseEntity.ok(resources);
	  }
	
	
	/*
	 * Will do it later
	 * @GetMapping("/{bankId}")
	public ResponseEntity<Resources<BankResource>> getBanksForUserById(@PathVariable final int customerId, @PathVariable final int bankId) {
		
		List<Bank> collection = bankService.getBankForUser(bankId);
		List<BankResource> brList=new ArrayList<>();
		
		for (Bank bank : collection) {
			
			BankResource br =new BankResource();
			BeanUtils.copyProperties(bank, br);
			brList.add(br);
		}
		
		
	    final Resources<BankResource> resources = new Resources<>(brList);
	    final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
	    resources.add(new Link(uriString, "self"));
	    return ResponseEntity.ok(resources);
	  }
*/
}
