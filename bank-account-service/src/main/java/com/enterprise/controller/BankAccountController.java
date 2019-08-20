package com.enterprise.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<UserDetail> updateBankAccount(@PathVariable("customerId") long customerId,@RequestBody UserDetailResource customerDto) {
	      
		UserDetail updatedCustomer = accountService.updateBankAccount(customerDto);
		return new ResponseEntity<UserDetail>(updatedCustomer, HttpStatus.OK);
	 }

	@DeleteMapping("/{customerId}")
	 ResponseEntity<Void> deleteBankAccount(@PathVariable int customerId) {
		
		return new ResponseEntity<Void>(accountService.deleteBankAccount(customerId) ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<UserDetail> getBankAccount(@PathVariable int customerId) {
		
		UserDetail customer = accountService.getBankAccount(customerId);
		return new ResponseEntity<UserDetail>(customer, HttpStatus.OK); 
	}
		
	@GetMapping
	public ResponseEntity<List<UserDetail>> getBankAccounts() {
		
		return new ResponseEntity<List<UserDetail>>(accountService.getBankAccounts(), HttpStatus.OK);
	}
}
