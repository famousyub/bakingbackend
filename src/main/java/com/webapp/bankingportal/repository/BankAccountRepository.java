package com.webapp.bankingportal.repository;

import com.webapp.bankingportal.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
