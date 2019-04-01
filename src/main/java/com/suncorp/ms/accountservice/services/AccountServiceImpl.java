package com.suncorp.ms.accountservice.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suncorp.ms.accountservice.model.Account;
import com.suncorp.ms.accountservice.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public List<Account> getAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public Account createAccount(Account account) {
		account.setAccountCreationDate(new Date());
		return accountRepository.save(account);
	}

	@Override
	public Account getAccount(Long accountId) {
	Optional<Account> opAccount = accountRepository.findById(accountId);
		if (opAccount.isPresent()) {
			return opAccount.get();
		}
		return null;
	}

	@Override
	public Account updateAccount(Account account) {
		return accountRepository.save(account);
	}

}
