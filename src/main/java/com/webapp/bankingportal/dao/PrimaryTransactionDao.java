package com.webapp.bankingportal.dao;


import com.webapp.bankingportal.entity.PrimaryTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long> {
    List<PrimaryTransaction> findAll();
}
