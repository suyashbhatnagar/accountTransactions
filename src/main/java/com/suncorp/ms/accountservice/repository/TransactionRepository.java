package com.suncorp.ms.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suncorp.ms.accountservice.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
