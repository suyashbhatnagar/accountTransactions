package com.suncorp.ms.accountservice.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Transaction implements Serializable {

	@Id
	@GeneratedValue
	private Long transactionId;
	private String transactionType;
	private Long creditAccountId;
	private Long debitAccountId;
	private Double ammount;
	private String transactionStatus;
	
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Long getCreditAccountId() {
		return creditAccountId;
	}
	public void setCreditAccountId(Long creditAccountId) {
		this.creditAccountId = creditAccountId;
	}
	public Long getDebitAccountId() {
		return debitAccountId;
	}
	public void setDebitAccountId(Long debitAccountId) {
		this.debitAccountId = debitAccountId;
	}
	public Double getAmmount() {
		return ammount;
	}
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
}
