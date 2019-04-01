package com.suncorp.ms.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suncorp.ms.accountservice.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
