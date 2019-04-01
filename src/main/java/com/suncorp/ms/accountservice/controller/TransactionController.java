package com.suncorp.ms.accountservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.suncorp.ms.accountservice.model.Transaction;
import com.suncorp.ms.accountservice.services.TransactionService;

@RestController
@RequestMapping(value="/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping(value="/", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getTransaction(){
		return transactionService.getTransactions();
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> executeTransaction(@RequestBody Transaction transaction) {
		try {
			Transaction executedTransaction = transactionService.executeTransaction(transaction);
			if(executedTransaction != null) {
				return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);	
	}
}
