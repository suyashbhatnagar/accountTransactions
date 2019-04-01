package com.suncorp.ms.accountservice.services;

import java.util.List;

import com.suncorp.ms.accountservice.model.Transaction;

public interface TransactionService {

	public List<Transaction> getTransactions();
	public Transaction executeTransaction(Transaction transaction) throws Exception; 
}
