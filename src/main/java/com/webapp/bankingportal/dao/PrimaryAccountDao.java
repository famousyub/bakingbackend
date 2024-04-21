package com.webapp.bankingportal.dao;


import com.webapp.bankingportal.entity.PrimaryAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long> {

    PrimaryAccount findByAccountNumber(String accountNumber);
}