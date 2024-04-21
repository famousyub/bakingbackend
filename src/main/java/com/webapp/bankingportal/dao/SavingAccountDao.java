package com.webapp.bankingportal.dao;


import com.webapp.bankingportal.entity.SavingsAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SavingAccountDao extends CrudRepository<SavingsAccount, Long> {

    SavingsAccount findByAccountNumber (String accountNumber);
}
