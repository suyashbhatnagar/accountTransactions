package com.suncorp.ms.accountservice.services;

import java.util.List;

import com.suncorp.ms.accountservice.model.Account;

public interface AccountService {

	public List<Account> getAccounts();
	public Account createAccount(Account account);
	public Account getAccount(Long accountId);
	public Account updateAccount(Account account);
}
