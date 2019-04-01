package com.suncorp.ms.accountservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suncorp.ms.accountservice.model.Account;
import com.suncorp.ms.accountservice.model.Transaction;
import com.suncorp.ms.accountservice.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountService accountService;
	
	@Override
	public List<Transaction> getTransactions() {
		return transactionRepository.findAll();
	}

	@Override
	public Transaction executeTransaction(Transaction transaction) throws Exception{
		
		switch (transaction.getTransactionType().toUpperCase()) {
		case "DEPOSITE":
			return executeDepositeTranasction(transaction);
		case "WITHDRAWAL":
			return executeWithdrawalTranasction(transaction);
		case "TRANSFER":
			return executeTransferTranasction(transaction);
		default:
			throw new Exception("Invalid operation type: " + transaction.getTransactionType());
		}
	}

	private Transaction executeDepositeTranasction(Transaction transaction) {
		Account depositeAccount = accountService.getAccount(transaction.getCreditAccountId());
		if(depositeAccount != null) {
			depositeAccount.setCurrentBalance(depositeAccount.getCurrentBalance() + transaction.getAmmount());
			Account updatedDepositeAccount = accountService.updateAccount(depositeAccount);
			if(updatedDepositeAccount != null) {
				transaction.setTransactionStatus("EXECUTED");
			} else {
				transaction.setTransactionStatus("REJECTED");
			}
			return transactionRepository.save(transaction);
		}
		return null;
	}

	private Transaction executeWithdrawalTranasction(Transaction transaction) {
		Account withdrawalAccount = accountService.getAccount(transaction.getDebitAccountId());
		if(withdrawalAccount != null) {
			Double netBalance = withdrawalAccount.getCurrentBalance() - transaction.getAmmount();
			if (netBalance >= 0) {
				withdrawalAccount.setCurrentBalance(netBalance);
				Account updatedWithdrawalAccount = accountService.updateAccount(withdrawalAccount);
				if(updatedWithdrawalAccount != null) {
					transaction.setTransactionStatus("EXECUTED");
				} else {
					transaction.setTransactionStatus("REJECTED");
				}
			} else {
				transaction.setTransactionStatus("REJECTED");
			}
			return transactionRepository.save(transaction);
		}
		return null;
	}

	private Transaction executeTransferTranasction(Transaction transaction) {
		Account depositeAccount = accountService.getAccount(transaction.getCreditAccountId());
		Account withdrawalAccount = accountService.getAccount(transaction.getDebitAccountId());
		if (depositeAccount != null && withdrawalAccount != null) {
			Double netBalance = withdrawalAccount.getCurrentBalance() - transaction.getAmmount();
			if (netBalance >= 0) {
				withdrawalAccount.setCurrentBalance(netBalance);
				depositeAccount.setCurrentBalance(depositeAccount.getCurrentBalance() + transaction.getAmmount());
				Account updatedWithdrawalAccount = accountService.updateAccount(withdrawalAccount);
				Account updatedDepositeAccount = accountService.updateAccount(depositeAccount);
				if (updatedWithdrawalAccount != null && updatedDepositeAccount != null) {
					transaction.setTransactionStatus("EXECUTED");
				} else {
					transaction.setTransactionStatus("REJECTED");
				}
			} else {
				transaction.setTransactionStatus("REJECTED");
			}
			return transactionRepository.save(transaction);
		}
		return null;
	}
}
