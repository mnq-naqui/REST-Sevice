package com.enterprise.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.enterprise.dto.UserDetailResource;
import com.enterprise.entity.AccountDetail;
import com.enterprise.entity.UserDetail;
import com.enterprise.service.AccountDetailService;
import com.enterprise.service.AccountMappingService;
import com.enterprise.service.BankAccountService;

@RestController
@RequestMapping(path="/bankaccount") 
public class BankAccountController {

	@Autowired
	private BankAccountService accountService;
	
	@Autowired
	private AccountDetailService accountDetailService;
	
	@Autowired
	private AccountMappingService accountMappingService;
	
	@PostMapping
    public ResponseEntity<UserDetailResource> createBankAccount(@RequestBody UserDetailResource customerDto) {
		
		final UserDetail userDetail = accountService.createBankAccount(customerDto);
	    final URI uri =
	        MvcUriComponentsBuilder.fromController(getClass())
	            .path("/{id}")
	            .buildAndExpand(userDetail.getUserId())
	            .toUri();
	    
	    UserDetailResource userDetailResource = new UserDetailResource();
	    BeanUtils.copyProperties(userDetail, userDetailResource);
	    AccountDetail accDetail = accountDetailService.createAccountDetail();
	    accountMappingService.createBankAccount(userDetailResource, accDetail.getAccountDetailId());
	    
	    userDetailResource.add(linkTo(BankAccountController.class).withRel("bankaccounts"));
	    userDetailResource.add(linkTo(methodOn(BankController.class).getBanksForUser(userDetailResource.getUserId())).withRel("banks"));
	    userDetailResource.add(linkTo(methodOn(BankAccountController.class).getBankAccount(userDetailResource.getUserId())).withSelfRel());
	    
	    return ResponseEntity.created(uri).body(userDetailResource);
    }
	
	@PutMapping(value = "/{customerId}")
    public ResponseEntity<UserDetailResource> updateBankAccount(@PathVariable("customerId") int customerId,@RequestBody UserDetailResource customerDto) {
	    
		customerDto.setUserId(customerId);
		UserDetail updatedCustomer = accountService.updateBankAccount(customerDto);
		UserDetailResource userDetailResource = new UserDetailResource();
		BeanUtils.copyProperties(updatedCustomer, userDetailResource);
		    
	    userDetailResource.add(linkTo(BankAccountController.class).withRel("bankaccounts"));
	    userDetailResource.add(linkTo(methodOn(BankController.class).getBanksForUser(userDetailResource.getUserId())).withRel("banks"));
		userDetailResource.add(linkTo(methodOn(BankAccountController.class).getBankAccount(userDetailResource.getUserId())).withSelfRel());

		return ResponseEntity.ok(userDetailResource);
	 }

	@DeleteMapping("/{customerId}")
	 ResponseEntity<Void> deleteBankAccount(@PathVariable int customerId) {
		
		return new ResponseEntity<Void>(accountService.deleteBankAccount(customerId) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{customerId}")
	public  ResponseEntity<UserDetailResource> getBankAccount(@PathVariable int customerId) {
		
		UserDetail userDetail = accountService.getBankAccount(customerId);
	
		UserDetailResource userDetailResource = new UserDetailResource();
		BeanUtils.copyProperties(userDetail, userDetailResource);
		    
	    userDetailResource.add(linkTo(BankAccountController.class).withRel("bankaccounts"));
		userDetailResource.add(linkTo(methodOn(BankController.class).getBanksForUser(userDetailResource.getUserId())).withRel("banks"));
		userDetailResource.add(linkTo(methodOn(BankAccountController.class).getBankAccount(userDetailResource.getUserId())).withSelfRel());
		    
		return ResponseEntity.ok(userDetailResource);
	}
		
	@GetMapping
	public ResponseEntity<Resources<UserDetailResource>> getBankAccounts() {
		
		List<UserDetail> collection = accountService.getBankAccounts();
		List<UserDetailResource> brList=new ArrayList<>();
		
		for (UserDetail bank : collection) {
			
			UserDetailResource br =new UserDetailResource();
			
			BeanUtils.copyProperties(bank, br);
			br.add(linkTo(BankAccountController.class).withRel("bankaccounts"));
			br.add(linkTo(methodOn(BankController.class).getBanksForUser(br.getUserId())).withRel("banks"));
			br.add(linkTo(methodOn(BankAccountController.class).getBankAccount(br.getUserId())).withSelfRel());

			brList.add(br);
		}
		
	    final Resources<UserDetailResource> resources = new Resources<>(brList);
	    final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
	    resources.add(new Link(uriString, "self"));
	    return ResponseEntity.ok(resources);
	  
	}
}
