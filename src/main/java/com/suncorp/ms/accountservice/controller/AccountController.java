package com.suncorp.ms.accountservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.suncorp.ms.accountservice.model.Account;
import com.suncorp.ms.accountservice.services.AccountService;

@RestController
@RequestMapping(value="/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="/", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getAccounts() {
		return accountService.getAccounts();
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Account createAccount(@RequestBody Account account) {
		return accountService.createAccount(account);
	}
	
	@RequestMapping(value="/{accountId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> getAccount(@PathVariable ("accountId") Long accountId) {
		Account account = accountService.getAccount(accountId);
		if (account != null) {
			return new ResponseEntity<Account>(account, HttpStatus.OK); 
		}
		return new ResponseEntity<Account>(HttpStatus.NOT_FOUND); 
	}
	
	@RequestMapping(value="/{accountId}", method=RequestMethod.PATCH, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> updateAccountType(@PathVariable ("accountId") Long accountId, @RequestBody String accountType) {
		Optional<Account> accountOptional = Optional.of(accountService.getAccount(accountId));
		if (accountOptional.isPresent()) {
			Account updatedAccount = accountOptional.get();
			updatedAccount.setAccountType(accountType);
			return new ResponseEntity<Account>(accountService.updateAccount(updatedAccount), HttpStatus.OK);
		}
		return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
	}
}
