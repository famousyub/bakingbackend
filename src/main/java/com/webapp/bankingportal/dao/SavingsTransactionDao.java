package com.webapp.bankingportal.dao;


import com.webapp.bankingportal.entity.SavingsTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SavingsTransactionDao extends CrudRepository<SavingsTransaction,Long> {
    List<SavingsTransaction> findAll();
}
